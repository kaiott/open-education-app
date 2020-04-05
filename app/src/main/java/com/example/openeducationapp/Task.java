package com.example.openeducationapp;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Task implements Serializable, Comparable<Task> {

    public static HashMap<Integer, Task> tasks;

    private int taskID;
    private String title, description;
    private Course course;
    private Calendar dateCreated, dueDate;
    private boolean isDone;
    private String imagePath;

    public Task(){
        this.taskID = 0;
        this.title = "title";
        this.description = "description";
        this.course = null;
        this.dateCreated = Calendar.getInstance();
        this.dueDate = Calendar.getInstance();
        this.isDone = false;
        this.imagePath = null;
    }

    public Task(int id, String title, String description, Course course, Calendar dateCreated, Calendar dueDate, boolean isDone, String imagePath) {
        this.taskID = id;
        this.title = title;
        this.description = description;
        this.course = course;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.isDone = isDone;
        this.imagePath = imagePath;
    }

    public int getTaskID() {
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        return "how did this happen";
    }

    public static void addTask(Task task) {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
        tasks.put(task.taskID, task);
    }

    @Override
    public int compareTo(Task task) {
        return this.getDueDate().compareTo(task.dueDate);
    }
}
