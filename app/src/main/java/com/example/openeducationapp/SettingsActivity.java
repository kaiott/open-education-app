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

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    TextView txt_settings_history, txt_settings_textsize;
    SeekBar seekBar1, seekBar2;
    Button back;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int text_size, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
