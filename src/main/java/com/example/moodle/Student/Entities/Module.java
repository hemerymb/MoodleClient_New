package com.example.moodle.Student.Entities;

public class Module {
    private long cmid;
    private long sectionid;
    private String name;
    private String modname;
    private String modplural;
    private long downloadcontent;

    public Module() {
    }

    public long getCmid() {
        return cmid;
    }

    public void setCmid(long cmid) {
        this.cmid = cmid;
    }

    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }

    public String getModplural() {
        return modplural;
    }

    public void setModplural(String modplural) {
        this.modplural = modplural;
    }

    public long getDownloadcontent() {
        return downloadcontent;
    }

    public void setDownloadcontent(long downloadcontent) {
        this.downloadcontent = downloadcontent;
    }
}
