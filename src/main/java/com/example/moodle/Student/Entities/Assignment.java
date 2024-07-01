package com.example.moodle.Student.Entities;

public class Assignment {
    private long assignmentid;
    private long moduleid;
    private String assignmentname;
    private long created;
    private long duedate;
    private long attemptnumber;

    public Assignment() {
    }

    public long getAssignmentid() {
        return assignmentid;
    }

    public void setAssignmentid(long assignmentid) {
        this.assignmentid = assignmentid;
    }

    public long getModuleid() {
        return moduleid;
    }

    public void setModuleid(long moduleid) {
        this.moduleid = moduleid;
    }

    public String getAssignmentname() {
        return assignmentname;
    }

    public void setAssignmentname(String assignmentname) {
        this.assignmentname = assignmentname;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getDuedate() {
        return duedate;
    }

    public void setDuedate(long duedate) {
        this.duedate = duedate;
    }

    public long getAttemptnumber() {
        return attemptnumber;
    }

    public void setAttemptnumber(long attemptnumber) {
        this.attemptnumber = attemptnumber;
    }
}
