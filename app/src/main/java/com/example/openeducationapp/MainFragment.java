package com.example.openeducationapp;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int fragment;
        fragment = R.layout.fragment_recycler;

        View view = inflater.inflate(fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.Adapter myAdapter;
        switch (getArguments().getInt("id")) {
            case R.id.nav_tasks:
                ArrayList<Task> tasks= new ArrayList<>();
                boolean showPast = getActivity().getSharedPreferences("init", Context.MODE_PRIVATE).getBoolean("filterSelection0", true);
                boolean showDone = getActivity().getSharedPreferences("init", Context.MODE_PRIVATE).getBoolean("filterSelection1", true);
                Calendar yesterday = Calendar.getInstance();
                yesterday.add(Calendar.DAY_OF_MONTH, -1);
                int course_id = getArguments().getInt("course_id",-1);
                for (Task task : Task.tasks.values()) {
                    if ((showPast || task.getDueDate().compareTo(yesterday) > 0) &&
                            (showDone || !task.isDone()) &&
                            (course_id == -1 || course_id == task.getCourse().getCourseID() ) ) {
                        tasks.add(task);
                    }
                }
                Collections.sort(tasks);
                myAdapter = new TaskTileAdapter(getActivity(), tasks);
                break;
            case R.id.nav_classroom:
                myAdapter = new CourseTileAdapter(getActivity(), 15);
                break;
            case R.id.nav_chat:
                myAdapter = new CourseTileAdapter(getActivity(), 15);
                break;
            default: myAdapter = new CourseTileAdapter(getActivity(), 15);
        }
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public static MainFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        MainFragment f = new MainFragment();
        f.setArguments(args);
        return f;
    }
    public static MainFragment newInstance(int id, int course_id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("course_id", course_id);
        MainFragment f = new MainFragment();
        f.setArguments(args);
        return f;
    }
}
