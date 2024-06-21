package com.example.moodle.Teacher.AssignmentPanel;

import java.sql.Date;

public class Assignment {
    private int id;
    private String assignmentName;
    private Date createdDate;
    private Date limitedDate;
    private String courseName;
    private String statut;

    public Assignment(int id, String assignmentName, Date createdDate, Date limitedDate, String courseName, String statut) {
        this.id = id;
        this.assignmentName = assignmentName;
        this.createdDate = createdDate;
        this.limitedDate = limitedDate;
        this.courseName = courseName;
        this.statut = statut;
    }

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
}
