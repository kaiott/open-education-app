package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskDetailActivity extends AppCompatActivity {

    private static final String TAG = "TaskDetailActivity";

    TextView txt_task_title, txt_task_description, txt_todo;
    Button btn_done;
    ImageButton displayImageButton;

    Task task;

    int task_id;
    int text_size;

    private static final int IMAGE_REQUEST = 1;
    String currentImagePath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        txt_task_title = findViewById(R.id.txt_task_title);
        txt_task_description = findViewById(R.id.txt_task_description);
        txt_todo = findViewById(R.id.txt_task_dueDate);
        btn_done = findViewById(R.id.btn_done);

        displayImageButton = findViewById(R.id.display_image_button);

        // Get current ID from Intent and Tasks from shared Preferences
        getData();

        // Set titles and content
        setData();

        if (task.getImagePath() == null) {
            displayImageButton.setAlpha(0.5f);
            displayImageButton.setEnabled(false);
        }
        else {
            displayImageButton.setAlpha(1f);
            displayImageButton.setEnabled(true);
        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setDone(!task.isDone());
                btn_done.setSelected(task.isDone());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (task.getImagePath() == null) {
            displayImageButton.setAlpha(0.5f);
            displayImageButton.setEnabled(false);
        }
        else {
            displayImageButton.setAlpha(1f);
            displayImageButton.setEnabled(true);
        }
    }

    // Get current ID from Intent and textsize from shared Preferences
    private void getData() {
        task = new Task();
        if (getIntent().hasExtra("task_id")) {
            task_id = getIntent().getIntExtra("task_id", -1);
            if (task_id != -1) {
                task = Task.tasks.get(task_id);
            }
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("settings",MODE_PRIVATE);
        text_size = sharedPreferences.getInt("text_size",18);
    }

    private void setData() {
        txt_task_title.setText(task.getTitle());
        txt_task_description.setText(task.getDescription());
        txt_todo.setText(task.formattedDueDate());

        btn_done.setSelected(task.isDone());

        txt_task_description.setTextSize(text_size);
    }

    public void captureImage(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;

            try {
                imageFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.openeducationapp.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);
            }
        }
    }

    public void displayImage(View view) {
        displayImageButton.setEnabled(false);
        Intent intent = new Intent(this, DisplayImageActivity.class);
        intent.putExtra("image_path", task.getImagePath());
        startActivity(intent);
    }

    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName="jpg_" + timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        task.setImagePath(currentImagePath);
        displayImageButton.setEnabled(true);
        displayImageButton.setAlpha(1f);
        return imageFile;
    }
}
