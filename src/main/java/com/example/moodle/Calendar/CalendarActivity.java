package com.example.moodle.Calendar;

import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String courseName;
    private String courseDuration;

    public CalendarActivity(ZonedDateTime date, String courseName, String courseDuration) {
        this.date = date;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    @Override
    public String toString() {
        return courseName + " (" + courseDuration + ")";
    }
}
