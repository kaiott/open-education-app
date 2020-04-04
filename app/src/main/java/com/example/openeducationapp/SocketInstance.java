package com.example.openeducationapp;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketInstance extends Application {
    private com.github.nkzawa.socketio.client.Socket iSocket;
    private static final String URL = "http://powermail.icu";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            IO.Options opts = new IO.Options();
            opts.query = "auth_token=";
            iSocket = IO.socket(URL, opts);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocketInstance() {
        return iSocket;
    }
}