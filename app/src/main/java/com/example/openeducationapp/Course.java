package com.example.openeducationapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Course implements Serializable {

    // Hashmap with all courses
    public static HashMap<Long, Course> courses;

    private long courseID;
    private String name;
    private String abbreviation;

    public long getCourseID() {
        return courseID;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Course(long id, String name, String abbreviation) {
        this.courseID = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Course() {

    }

    public static void addCourse(Course course) {
        if (courses == null) {
            courses = new HashMap<>();
        }
        courses.put(course.courseID, course);
    }

    public ArrayList<Task> getTaskByCourse () {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : Task.tasks.values()) {
            if (task.getCourse().courseID == this.courseID) {
                result.add(task);
            }
        }
        return result;
    }

    public class Ratio {
        private int done;
        private int total;

        public Ratio(int done, int total) {
            this.done = done;
            this.total = total;
        }

        public int getDone() {
            return done;
        }

        public int getTotal() {
            return total;
        }

        @NonNull
        @Override
        public String toString() {
            return String.format(Locale.GERMAN,"%d/%s", done, total);
        }
    }

    public Ratio numberOfTasks() {
        int done = 0, total = 0;
        for (Task task : getTaskByCourse()) {
            if (task.isDone()) {
                done++;
            }
            total++;
        }
        return new Ratio(done, total);
    }
}
