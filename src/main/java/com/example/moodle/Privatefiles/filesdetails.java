package com.example.moodle.Privatefiles;

public class filesdetails {
    private String fileName;
    private String fileSize;
    private String fileType;
    private String filePath;

    public filesdetails(String fileName, String fileSize, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }
}
