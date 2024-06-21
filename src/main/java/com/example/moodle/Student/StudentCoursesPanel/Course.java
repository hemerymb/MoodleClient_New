package com.example.moodle.Student.StudentCoursesPanel;

public class Course {
    private String courseName;
    private String description;
    private int nbChapters;

    public Course(String courseName, String description, int nbChapters) {
        this.courseName = courseName;
        this.description = description;
        this.nbChapters = nbChapters;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbChapters() {
        return nbChapters;
    }

    public void setNbChapters(int nbChapters) {
        this.nbChapters = nbChapters;
    }
}
