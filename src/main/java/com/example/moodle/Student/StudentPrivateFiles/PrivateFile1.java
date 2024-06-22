package com.example.moodle.Student.StudentPrivateFiles;

public class PrivateFile1 {
    private int id;
    private String fileName;
    private long fileSize;
    private String fileType;
    private String filePath;

    public PrivateFile1(int id, String fileName, long fileSize, String fileType, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }
}

