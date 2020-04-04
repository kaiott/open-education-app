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

    public TaskTileAdapter(Context ct, ArrayList<Task> tasks) {
        context = ct;
        this.tasks = tasks;
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

        int flag_id;
        switch (tasks.get(position).getCourse().getAbbreviation()){
            case "Math":
                flag_id = R.drawable.task_flag_math;
                break;
            case "DE":
                flag_id = R.drawable.task_flag_de;
                break;
            case "Franz":
                flag_id = R.drawable.task_flag_fr;
                break;
            case "GE":
                flag_id = R.drawable.task_flag_ge;
                break;
            case "Eng":
                flag_id=R.drawable.task_flag_en;
                break;
            case "NNM":
                flag_id = R.drawable.task_flag_nmm;
                break;
            default:
                flag_id = R.drawable.task_flag_de;
        }

        holder.titleText.setText(tasks.get(position).getTitle());
        holder.detailsText.setText(tasks.get(position).getDescription());
        holder.typeText.setText(tasks.get(position).getCourse().getAbbreviation());
        holder.deadlineText.setText(tasks.get(position).formattedDueDate());
        holder.statusImage.setVisibility(tasks.get(position).isDone() ? View.VISIBLE: View.INVISIBLE);
        holder.task_flag.setImageResource(flag_id);
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
        ImageView statusImage, task_flag;
        ConstraintLayout constraintLayout;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.task_title);
            detailsText = itemView.findViewById(R.id.task_details);
            deadlineText = itemView.findViewById(R.id.task_deadline);
            typeText = itemView.findViewById(R.id.task_type);
            statusImage = itemView.findViewById(R.id.task_status);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
            task_flag = itemView.findViewById(R.id.task_flag);
        }
    }
}
