package com.example.moodle.Student.Entities;

public class Course {
    private int id;
    private String courseName;
    private String courseAbr;
    private String courseDescription;
    private int nbChapters;
    private int nbAssignments;

    public Course(int id, String courseName, String courseAbr, String courseDescription, int nbChapters, int nbAssignments) {
        this.id = id;
        this.courseName = courseName;
        this.courseAbr = courseAbr;
        this.courseDescription = courseDescription;
        this.nbChapters = nbChapters;
        this.nbAssignments = nbAssignments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAbr() {
        return courseAbr;
    }

    public void setCourseAbr(String courseAbr) {
        this.courseAbr = courseAbr;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getNbChapters() {
        return nbChapters;
    }

    public void setNbChapters(int nbChapters) {
        this.nbChapters = nbChapters;
    }

    public int getNbAssignments() {
        return nbAssignments;
    }

    public void setNbAssignments(int nbAssignments) {
        this.nbAssignments = nbAssignments;
    }
}
