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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TaskTileAdapter extends RecyclerView.Adapter<TaskTileAdapter.EventViewHolder> {

    ArrayList<Task> tasks;
    private String [] courses_names;
    private Context context;

    public TaskTileAdapter(Context ct, String courses_names[]) {
        context = ct;
        tasks = new ArrayList<>();
        for (String c : courses_names) {
            Calendar dueDate = Calendar.getInstance();
            dueDate.set(Calendar.DAY_OF_MONTH, (int) (Math.random()*29) + 1);
            dueDate.set(Calendar.MONTH, (int) (Math.random()*3) + 3);

            Task task = new Task(0, "Textbuch lesen", String.format(Locale.GERMAN,
                    "Lies die Seiten 33-35 im Textbuch %s und löse die Übungen",
                    c), null, null, dueDate, Math.random() < 0.5);
            tasks.add(task);
        }
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

        holder.titleText.setText(tasks.get(position).getTitle());
        holder.detailsText.setText(tasks.get(position).getDescription());
        holder.typeText.setText(courses_names[position].substring(0,2));
        holder.deadlineText.setText(tasks.get(position).formattedDueDate());
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
