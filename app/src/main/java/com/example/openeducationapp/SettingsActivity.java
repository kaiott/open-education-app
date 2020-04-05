package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    TextView txt_settings_history, txt_settings_textsize;
    SeekBar seekBar1, seekBar2;
    Button btn_signout, back;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Socket socket;

    int text_size, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            socket = IO.socket("http://10.10.41.101:8000");
            socket.connect();
            Log.d(TAG, "onCreate: Socket connection");
            Log.d(TAG, "onCreate: socket connected "+socket.connected());
        } catch (URISyntaxException e) {
            Log.e("HERE", e.getMessage());
        }

        Log.d(TAG, "onCreate: socket connected "+socket.connected());
        if (socket.connected()){
            Toast.makeText(SettingsActivity.this, "Socket Connected!!",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate: Socket connected!!!");
        }



        btn_signout = findViewById(R.id.btn_signout);
        back = findViewById(R.id.btn_back);
        txt_settings_textsize = findViewById(R.id.txt_settings_textsize);
        txt_settings_history = findViewById(R.id.txt_settings_history);
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        text_size=sharedPreferences.getInt("text_size",18);
        history=sharedPreferences.getInt("history",2);
        editor = sharedPreferences.edit();

        seekBar1.setProgress(text_size,true);
        seekBar2.setProgress(history,true);
        txt_settings_textsize.setText(""+text_size+" sp");
        txt_settings_history.setText(""+history+" Wochen");

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editor.putInt("text_size", progress);
                editor.apply();
                txt_settings_textsize.setText("" +progress+" sp");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editor.putInt("history", progress);
                editor.apply();
                txt_settings_history.setText("" +progress+" Wochen");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply();
                Toast.makeText(SettingsActivity.this, "Abmelden", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }));

    }
}
