package com.example.moodle.Student.Entities;

public class Grade {
    private long gradeid;
    private long submissionid;
    private long grade;
    private long grader; // Celui qui note
    private String comment;

    public Grade() {
    }

    public long getGradeid() {
        return gradeid;
    }

    public void setGradeid(long gradeid) {
        this.gradeid = gradeid;
    }

    public long getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(long submissionid) {
        this.submissionid = submissionid;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getGrader() {
        return grader;
    }

    public void setGrader(long grader) {
        this.grader = grader;
    }
}
