package com.example.openeducationapp;

import java.io.Serializable;
import java.util.HashMap;

public class Course implements Serializable {

    // Hashmap with all courses
    public static HashMap<Long, Course> courses;


    long courseID;
    String name;
    String abbreviation;

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
}
