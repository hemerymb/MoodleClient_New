package com.example.moodle.Teacher.entity;

public class CoursePull {
    public int id;
    String fullname;
    String shortname;
    long startdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    long enddate;


    public CoursePull(int id, String fullname, String shortname, long startdate, long enddate) {
        this.id = id;
        this.fullname = fullname;
        this.shortname = shortname;
        this.startdate = startdate;
        this.enddate = enddate;

    }
}
