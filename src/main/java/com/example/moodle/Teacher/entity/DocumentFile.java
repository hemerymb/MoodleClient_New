package com.example.moodle.Teacher.entity;

public class DocumentFile {
    private int id;
    private String fileName;
    private long fileSize;
    private String fileType;
    private String filePath;
    private int chapterId;

    public DocumentFile(int id, String fileName, long fileSize, String fileType, String filePath, int chapterId) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
        this.chapterId = chapterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
}
