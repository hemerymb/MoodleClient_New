package com.example.moodle.Teacher.entity;

public class Chapter {
    private int id;
    private String title;
    private int num;
    private String content;
    private int courseId;


    public Chapter(int id, String title, int num, String content, int courseId) {
        this.id = id;
        this.title = title;
        this.num = num;
        this.content = content;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
