package com.example.openeducationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    TextView txt_settings_history, txt_settings_textsize;
    SeekBar seekBar1, seekBar2;
    Button btn_signout, back;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int text_size, history;

    private Socket mSocket;
    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            mSocket = IO.socket("http://chat.powermail.icu");
        } catch (URISyntaxException e) {}
        mSocket.connect();

        //connectWebSocket();




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
                //sendMessage(v);
                attemptSend();
                Log.d(TAG, "onClick: send message "+ mSocket.connected());
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

    /*
    private void connectWebSocket() {
        Log.d(TAG, "connectWebSocket: ");
        URI uri;
        try {
            uri = new URI("ws://powermail.icu:8000");
            Log.d(TAG, "connectWebSocket: uri");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            private static final String TAG = "SettingsActivity";

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.d(TAG, "onOpen: handshake");
                Log.i("Websocket", "Opened");
                JSONObject obj = new JSONObject();
                try {
                    obj.accumulate("getTasks", 20);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String jsonString = null;
                try {
                    jsonString = obj.toString(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWebSocketClient.send("42" + jsonString);

            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: received Message: "+message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
        Log.d(TAG, "connectWebSocket: "+mWebSocketClient.isClosed());
    }

    public void sendMessage(View view) {
        Log.d(TAG, "sendMessage: send");
        JSONObject obj = new JSONObject();
        try {
            obj.accumulate("getTasks", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = null;
        try {
            jsonString = obj.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mWebSocketClient.send("42" + jsonString);
    }


     */
    private void attemptSend() {
        JSONObject obj = new JSONObject();
        try {
            obj.accumulate("getTasks", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = null;
        try {
            jsonString = obj.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("42", jsonString);
        mSocket.connect();
    }
}
