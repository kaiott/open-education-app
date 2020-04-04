package com.example.openeducationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TaskTileAdapter extends RecyclerView.Adapter<TaskTileAdapter.EventViewHolder> {

    private String [] courses_names;
    private Context context;

    public TaskTileAdapter(Context ct, String courses_names[]) {
        context = ct;
        this.courses_names = courses_names;
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
        holder.titleText.setText(courses_names[position]);
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
