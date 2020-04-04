package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;

    String [] taskTitles = {"Textbuch lesen",
                            "Übungsheft",};
    String [] taskDetails = {"Seiten 33-35",
                            "Kapitel 2",
                            "Rest von Kapitel 4"};
    String [] courseNames = {"Mathematik", "Deutsch", "Französisch", "Geschichte", "English", "Natur Mensch Mitwelt"};
    String [] courseAbbs = {"Math", "DE", "Franz", "GE", "Eng", "NNM"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        for (int i = 0; i < courseNames.length; i++) {
            Course.addCourse(new Course(i, courseNames[i], courseAbbs[i]));
        }
        for (int i = 0; i < 60; i++) {
            Calendar dueDate = Calendar.getInstance();
            dueDate.set(Calendar.DAY_OF_MONTH, (int) (Math.random()*29) + 1);
            dueDate.set(Calendar.MONTH, (int) (Math.random()*3) + 3);

            Task task = new Task(i,
                    taskTitles[(int) (Math.random()*taskTitles.length)],
                    taskDetails[(int) (Math.random()*taskDetails.length)],
                    Course.courses.get((int) (Math.random()*Course.courses.size())),
                    null,
                    dueDate,
                    Math.random() < 0.67);
            Task.addTask(task);
        }

        String [] firstNames = {"Lara", "Lukas", "Maya", "Maurus", "Nicole", "Nils"};
        String [] lastNames = {"Müller", "Meier", "von Grünigen", "Berger", "Steiner"};
        String [] classNames = {"1a", "1b", "2a", "2b", "3a", "3b","4a", "4b", "5a", "5b", "6a", "6b"};

        Student.setStudent(new Student(0,
                firstNames[(int) (Math.random()*firstNames.length)],
                lastNames[(int) (Math.random()*lastNames.length)],
                classNames[(int) (Math.random()*classNames.length)]));


        String username = getSharedPreferences("user", MODE_PRIVATE).getString("username", "");
        if (!username.equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }

    public void loginButtonClicked(View view) {
        String username = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        resolveLoginAttempt(username, password);
    }

    protected void resolveLoginAttempt(String username, String password) {
        if (validateLogin(username, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            getSharedPreferences("user", MODE_PRIVATE).edit().putString("username", username).apply();
            finish();
        }
        else {
            Toast.makeText(this, "Email und Passwort stimmen nicht überein", Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean validateLogin(String username, String password) {
        if (username.equals("kai") || username.equals("lars")) {
            return true;
        }
        return false;
    }


}
