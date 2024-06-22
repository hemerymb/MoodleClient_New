package com.example.moodle.Teacher.AssignmentPanel;

import java.sql.Date;
import java.time.LocalDate;

public class Assignment {
    private int id;
    private String assignName;
    private Date createdDate;
    private Date limitedDate;
    private String courseName;
    private String statut;

    public Assignment(int id, String assignmentName, Date createdDate, Date limitedDate, String courseName) {
    }

    public Assignment(String assignName, Object courseName, LocalDate openLocalDate, LocalDate dueLocalDate) {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getAssignName() {
        return assignName;
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
        this.assignName = assignmentName;
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

    public String getAssignmentName() {
        return null;
    }
}
