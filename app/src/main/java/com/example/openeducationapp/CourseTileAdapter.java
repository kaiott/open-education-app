package com.example.openeducationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class CourseTileAdapter extends RecyclerView.Adapter<CourseTileAdapter.EventViewHolder> {

    private int max_courses;
    private Context context;

    public CourseTileAdapter(Context ct, int max_courses) {
        context = ct;
        this.max_courses = max_courses;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_tile, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        holder.titleText.setText(Course.courses.get((long) position).getName());
        Course.Ratio ratioTasksDone = Course.courses.get((long) position).numberOfTasks();
        holder.progressBar.setMax(ratioTasksDone.getTotal());
        holder.progressBar.setProgress(ratioTasksDone.getDone());
        holder.progressText.setText(ratioTasksDone.toString());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return Course.courses.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, progressText;
        ProgressBar progressBar;
        ConstraintLayout constraintLayout;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.course_title);
            progressText = itemView.findViewById(R.id.course_progress_text);
            progressBar = itemView.findViewById(R.id.course_progress_bar);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
