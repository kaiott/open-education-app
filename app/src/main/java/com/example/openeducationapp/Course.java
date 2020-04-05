package com.example.openeducationapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Course implements Serializable {

    // Hashmap with all courses
    public static HashMap<Integer, Course> courses;

    private int courseID;
    private String name;

    public int getCourseID() {
        return courseID;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return getAbbreviation(this.name);
    }

    public Course(int id, String name) {
        this.courseID = id;
        this.name = name;
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


    protected static Map<String, String> abbs = new HashMap<String, String> () {{
        put("Mathematik", "M");
        put("English", "E");
        put("Französisch", "F");
        put("Deutsch", "D");
        put("Biologie", "B");
        put("Geschichte", "Ge");
        put("Chemie", "C");
        put("Natur Mensch Mitwelt", "NNM");
    }};
    public String getAbbreviation(String course) {
        String res = abbs.get(course);
        if (res == null) {
            res = course.substring(0,2);
        }
        return res;
    }
}
