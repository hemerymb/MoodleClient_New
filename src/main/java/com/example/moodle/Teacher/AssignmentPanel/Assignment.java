package com.example.moodle.Teacher.AssignmentPanel;

import java.sql.Date;

public class Assignment {
    private int id;
    private String assignmentName;
    private Date createdDate;
    private Date limitedDate;
    private String courseName;
    private String statut;

    public Assignment(int id, String assignmentName, Date createdDate, Date limitedDate, String courseName) {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLimitedDate() {
        return limitedDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStatut() {
        return statut;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLimitedDate(Date limitedDate) {
        this.limitedDate = limitedDate;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
