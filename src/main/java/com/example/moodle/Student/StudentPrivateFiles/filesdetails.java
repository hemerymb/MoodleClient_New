package com.example.moodle.Student.StudentPrivateFiles;


public class filesdetails {
    private static String fileName;
    private String fileSize;
    private String fileType;
    private String filePath;
    private String readableFileSize;

    public filesdetails(String fileName, String fileType, String filePath, String readableFileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
        this.readableFileSize = readableFileSize;
    }

    public static String getfileName() {
        return fileName;
    }

    public String getfileSize() {
        return fileSize;
    }

    public String getfileType() {
        return fileType;
    }

    public String getfilePath() {
        return filePath;
    }

    public String getReadableFileSize() {
        return readableFileSize;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10s %-40s", fileName, fileSize, fileType, filePath);
    }
}
