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

    private String [] courses_names;
    private Context context;

    public CourseTileAdapter(Context ct, String courses_names[]) {
        context = ct;
        this.courses_names = courses_names;
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
        holder.titleText.setText(courses_names[position]);
        int num_tasks = getItemCount()-1; // (int) (Math.random()*10);
        int done_tasks = position; // (int) (Math.random()*num_tasks);
        holder.progressBar.setMax(num_tasks);
        holder.progressBar.setProgress(done_tasks);
        holder.progressText.setText(String.format(Locale.GERMAN, "%d/%s", done_tasks, num_tasks));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return courses_names.length;
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
