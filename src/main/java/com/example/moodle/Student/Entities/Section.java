package com.example.moodle.Student.Entities;

public class Section {
    private long sectionid;
    private String sectionname;
    private long courseid;

    public Section() {
    }

    public Section(long sectionid, String sectionname, long courseid) {
        this.sectionid = sectionid;
        this.sectionname = sectionname;
        this.courseid = courseid;
    }

    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }
}
