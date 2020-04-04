package com.example.openeducationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private int nav_item;
    public MainFragment(int nav_item) {
        this.nav_item = nav_item;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int fragment;
        switch (nav_item) {
            case R.id.nav_news:
                fragment = R.layout.fragment_news;
                break;
            case R.id.nav_classroom:
                fragment = R.layout.fragment_classroom;
                break;
            case R.id.nav_chat:
                fragment = R.layout.fragment_chat;
                break;
            default: fragment = R.layout.fragment_news;
        }
        return inflater.inflate(fragment, container, false);
    }
}
