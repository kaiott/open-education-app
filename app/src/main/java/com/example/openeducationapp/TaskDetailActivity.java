package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TaskDetailActivity extends AppCompatActivity {

    private static final String TAG = "TaskDetailActivity";

    TextView txt_task_title, txt_task_description, txt_todo;
    Button btn_done, btn_documents, btn_chatgroup;

    Task task;

    long task_id;
    int text_size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        txt_task_title = findViewById(R.id.txt_task_title);
        txt_task_description = findViewById(R.id.txt_task_description);
        txt_todo = findViewById(R.id.txt_task_dueDate);
        btn_chatgroup = findViewById(R.id.btn_chatgroup);
        btn_documents = findViewById(R.id.btn_download_docs);
        btn_done = findViewById(R.id.btn_done);

        // Get current ID from Intent and Tasks from shared Preferences
        getData();

        // Set titles and content
        setData();

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setDone(!task.isDone());
                btn_done.setSelected(task.isDone());
            }
        });

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

}
