package com.example.openeducationapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TaskTileAdapter extends RecyclerView.Adapter<TaskTileAdapter.EventViewHolder> {

    ArrayList<Task> tasks;
    private Context context;

    public TaskTileAdapter(Context ct, int maxtasks) {
        context = ct;
        tasks = new ArrayList<>();
        tasks.addAll(Task.tasks.values());
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_tile, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {

        holder.titleText.setText(tasks.get(position).getTitle());
        holder.detailsText.setText(tasks.get(position).getDescription());
        holder.typeText.setText(tasks.get(position).getCourse().getAbbreviation());
        holder.deadlineText.setText(tasks.get(position).formattedDueDate());
        holder.statusImage.setVisibility(tasks.get(position).isDone() ? View.VISIBLE: View.INVISIBLE);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TaskDetailActivity.class);
                intent.putExtra("task_id",tasks.get(position).getTaskID());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, detailsText, deadlineText, typeText;
        ImageView statusImage;
        ConstraintLayout constraintLayout;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.task_title);
            detailsText = itemView.findViewById(R.id.task_details);
            deadlineText = itemView.findViewById(R.id.task_deadline);
            typeText = itemView.findViewById(R.id.task_type);
            statusImage = itemView.findViewById(R.id.task_status);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
