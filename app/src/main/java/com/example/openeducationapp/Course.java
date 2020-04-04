package com.example.openeducationapp;

public class Course {
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
}
