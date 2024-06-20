package com.example.moodle.Privatefiles;

public class filesdetails {
    private String name;
    private String size;
    private String type;
    private String path;

    public filesdetails(String name, String size, String type, String path) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }
}
