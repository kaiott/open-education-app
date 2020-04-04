package com.example.openeducationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    private int nav_item;
    RecyclerView recyclerView;


    public MainFragment(int nav_item) {
        this.nav_item = nav_item;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int fragment;

        switch (nav_item) {
            case R.id.nav_tasks:
                fragment = R.layout.fragment_recycler;
                break;
            case R.id.nav_classroom:
                fragment = R.layout.fragment_recycler;
                break;
            case R.id.nav_chat:
                fragment = R.layout.fragment_recycler;
                break;
            default: fragment = R.layout.fragment_recycler;
        }
        View view = inflater.inflate(fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        String [] course_names ={"Mathematik", "Deutsch", "Französisch", "Geschichte", "English", "Natur Mensch Mitwelt"};
        RecyclerView.Adapter myAdapter;
        switch (nav_item) {
            case R.id.nav_tasks:
                myAdapter = new TaskTileAdapter(getActivity(), 15);
                break;
            case R.id.nav_classroom:
                myAdapter = new CourseTileAdapter(getActivity(), course_names);
                break;
            case R.id.nav_chat:
                myAdapter = new CourseTileAdapter(getActivity(), course_names);
                break;
            default: myAdapter = new CourseTileAdapter(getActivity(), course_names);
        }
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
