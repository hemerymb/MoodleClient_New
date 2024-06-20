package com.example.moodle.Calendar;

import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String CourseName;
    private Integer CourseDuration;

    public CalendarActivity(ZonedDateTime date, String clientName, Integer serviceNo) {
        this.date = date;
        this.CourseName = clientName;
        this.CourseDuration = serviceNo;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public Integer getCourseDuration() {
        return CourseDuration;
    }

    public void setCourseDuration(Integer CourseDuration) {
        this.CourseDuration = CourseDuration;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", CourseName='" + CourseName + '\'' +
                ", CourseDuration=" + CourseDuration +
                '}';
    }
}