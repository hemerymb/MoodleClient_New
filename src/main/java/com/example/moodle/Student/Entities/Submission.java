package com.example.moodle.Student.Entities;

public class Submission {
    private long submissionid;
    private long assignmentid;
    private String status;
    private long created;
    private long updated;
    private long studentid;

    public Submission() {
    }

    public long getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(long submissionid) {
        this.submissionid = submissionid;
    }

    public long getAssignmentid() {
        return assignmentid;
    }

    public void setAssignmentid(long assignmentid) {
        this.assignmentid = assignmentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
