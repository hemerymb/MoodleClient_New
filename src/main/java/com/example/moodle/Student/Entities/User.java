package com.example.moodle.Student.Entities;

public class User {
    private long userid;
    private String username;
    private String password;
    private String token;
    private String picture;
    private int role;

    public User() {
    }

    public User(int userid, String username, String password, String token, String picture, int role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.token = token;
        this.picture = picture;
        this.role = role;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
