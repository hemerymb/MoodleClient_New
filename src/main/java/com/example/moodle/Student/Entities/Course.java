package com.example.moodle.Student.Entities;

public class Course {
    private long courseid;
    private String fullname;
    private String shortname;
    private String summary;
    private int numsections;
    private long startdate;
    private long enddate;
    private long updated;
    private long studentid;
    private long teacherid;

    public Course() {
    }

    public Course(long courseid, String fullname, String shortname, String summary, int numsections, long startdate, long enddate, long updated, long studentid, long teacherid) {
        this.courseid = courseid;
        this.fullname = fullname;
        this.shortname = shortname;
        this.summary = summary;
        this.numsections = numsections;
        this.startdate = startdate;
        this.enddate = enddate;
        this.updated = updated;
        this.studentid = studentid;
        this.teacherid = teacherid;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getNumsections() {
        return numsections;
    }

    public void setNumsections(int numsections) {
        this.numsections = numsections;
    }

    public long getStartdate() {
        return startdate;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public long getEnddate() {
        return enddate;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(long teacherid) {
        this.teacherid = teacherid;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
