package com.example.openeducationapp;

public class Student {
    public static Student student;

    int studentID;
    String firstNames, lastNames, className;

    public Student(int studentID, String firstNames, String lastNames, String className) {
        this.studentID = studentID;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.className = className;
    }

    public static void setStudent(Student student) {
        Student.student = student;
    }

    public static Student getStudent() {
        return student;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getClassName() {
        return "Klasse " + className;
    }

    public String getFullName() {
        return firstNames + " " + lastNames;
    }
}
