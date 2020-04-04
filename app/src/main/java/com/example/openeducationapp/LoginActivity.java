package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
