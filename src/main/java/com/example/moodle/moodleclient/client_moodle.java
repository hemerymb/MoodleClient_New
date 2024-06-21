package com.example.moodle.moodleclient;

public class client_moodle {
    public String username;
    public boolean status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void  is_Teacher(boolean isTeacher) {
        status = isTeacher;
    }

    public boolean is_Teacher(){
        return status;
    }
}
