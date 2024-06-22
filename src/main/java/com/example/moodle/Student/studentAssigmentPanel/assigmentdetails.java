package com.example.moodle.Student.studentAssigmentPanel;

public class assigmentdetails {
    private String name;
    private long size;
    private String type;
    private String path;

    public assigmentdetails(String name, long size, String type, String path) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10s %-40s", name, size, type, path);
    }
}
