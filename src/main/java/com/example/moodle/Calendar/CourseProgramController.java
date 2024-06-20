package com.example.moodle.Calendar;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CourseProgramController {

    @FXML
    private TextField courseNameField;
    @FXML
    private TextField courseDurationField;
    @FXML
    private TextField courseDateField;

    private CalendarController calendarController;

    public void setCalendarController(CalendarController calendarController) {
        this.calendarController = calendarController;
    }

    public void setCourseDetails(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        courseDateField.setText(date.format(formatter));
    }

    @FXML
    private void handleOk() {
        String courseName = courseNameField.getText();
        String courseDuration = courseDurationField.getText();
        String dateString = courseDateField.getText();

        /*try {
            ZonedDateTime courseDate = ZonedDateTime.parse(dateString + "T00:00:00" + calendarController.getDateFocus().getOffset());
            CalendarActivity newActivity = new CalendarActivity(courseDate, courseName, Integer.parseInt(courseDuration));
            calendarController.addCourse(newActivity);
            Stage stage = (Stage) courseNameField.getScene().getWindow();
            stage.close();
        } catch (DateTimeParseException e) {
            // Handle error: invalid date format
            e.printStackTrace();
        }*/
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) courseNameField.getScene().getWindow();
        stage.close();
    }
}
