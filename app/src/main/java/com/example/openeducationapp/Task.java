package com.example.openeducationapp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Task {

    public static HashMap<Long, Task> tasks;

    private long taskID;

    private String title, description;
    private Course course;
    private Calendar dateCreated, dueDate;
    private boolean isDone;

    public Task(long id, String title, String description, Course course, Calendar dateCreated, Calendar dueDate, boolean isDone) {
        this.taskID = id;
        this.title = title;
        this.description = description;
        this.course = course;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public long getTaskID() {
        return taskID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Course getCourse() {
        return course;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return isDone;
    }


    public String formattedDueDate() {
        return String.format(Locale.GERMAN, "%s %02d.%02d.%02d",
                weekDayName(dueDate.get(Calendar.DAY_OF_WEEK)),
                dueDate.get(Calendar.DAY_OF_MONTH), dueDate.get(Calendar.MONTH)+1,
                dueDate.get(Calendar.YEAR));
    }

    private static String weekDayName(int day) {
        switch (day) {
            case 1:
                return "So";
            case 2:
                return "Mo";
            case 3:
                return "Di";
            case 4:
                return "Mi";
            case 5:
                return "Do";
            case 6:
                return "Fr";
            case 7:
                return "Sa";
        }
        return "dfasdfjoasfdj";
    }

    public static void addTask(Task task) {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
        tasks.put(task.taskID, task);
    }
}
