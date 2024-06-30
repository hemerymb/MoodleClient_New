package com.example.moodle.Student.Entities;

public class File {
    private long fileid;
    private long moduleid;
    private String filename;
    private String filepath;
    private long filesize;
    private String fileurl;
    private long created;
    private long updated;
    private long repositorytype;
    private String mimetype;

    public File() {
    }

    public long getFileid() {
        return fileid;
    }

    public void setFileid(long fileid) {
        this.fileid = fileid;
    }

    public long getModuleid() {
        return moduleid;
    }

    public void setModuleid(long moduleid) {
        this.moduleid = moduleid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
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

    public long getRepositorytype() {
        return repositorytype;
    }

    public void setRepositorytype(long repositorytype) {
        this.repositorytype = repositorytype;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
}
