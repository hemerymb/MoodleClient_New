package com.example.moodle.Teacher.CalendarPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @FXML
    private Button btnprogram;

    private Map<ZonedDateTime, List<CalendarActivity>> courseMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    private void openCourseProgramWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CourseProgram.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Programmer un nouveau cours");

            CourseProgramController controller = loader.getController();
            controller.setCalendarController(this);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(CalendarActivity course) {
        courseMap.putIfAbsent(course.getDate(), new ArrayList<>());
        courseMap.get(course.getDate()).add(course);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    private void programCourse(String courseName, String courseDuration, ZonedDateTime courseDate) {
        Map<Integer, List<CalendarActivity>> calendarActivities = getCalendarActivitiesMonth(dateFocus, courseName, courseDuration, courseDate);
        courseMap.put(courseDate, calendarActivities.get(courseDate.getDayOfMonth()));
        calendar.getChildren().clear();
        drawCalendar();
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus, String courseName, String courseDuration, ZonedDateTime courseDate) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();

        // Ajout de l'activité de cours
        calendarActivities.add(new CalendarActivity(courseDate, courseName, courseDuration));

        return createCalendarMap(calendarActivities);
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(dateFocus.getMonth().toString());

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        VBox vbox = new VBox();
                        Text dateText = new Text(String.valueOf(currentDate));
                        vbox.getChildren().add(dateText);

                        ZonedDateTime currentDateTime = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), currentDate, 0, 0, 0, 0, dateFocus.getZone());
                        stackPane.setOnMouseClicked(event -> showContextMenu(stackPane, currentDateTime));

                        if (courseMap.containsKey(currentDateTime)) {
                            List<CalendarActivity> activities = courseMap.get(currentDateTime);
                            for (CalendarActivity activity : activities) {
                                Text courseText = new Text(activity.getCourseName() + " (" + activity.getCourseDuration() + ")");
                                vbox.getChildren().add(courseText);
                            }
                        }

                        stackPane.getChildren().add(vbox);

                        if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                            rectangle.setStroke(Color.BLUE);
                        }
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void showContextMenu(StackPane stackPane, ZonedDateTime date) {
        ContextMenu contextMenu = new ContextMenu();

        TextField courseNameField = new TextField();
        courseNameField.setPromptText("Nom du cours");
        MenuItem courseNameItem = new MenuItem("", courseNameField);

        TextField courseDurationField = new TextField();
        courseDurationField.setPromptText("Durée (heures)");
        MenuItem courseDurationItem = new MenuItem("", courseDurationField);

        TextField courseDateField = new TextField();
        courseDateField.setPromptText("Date (yyyy-MM-dd)");
        courseDateField.setText(date.toLocalDate().toString());
        MenuItem courseDateItem = new MenuItem("", courseDateField);

        MenuItem submitItem = new MenuItem("Valider");
        submitItem.setOnAction(e -> {
            String name = courseNameField.getText();
            String duration = courseDurationField.getText();
            String dateString = courseDateField.getText();
            ZonedDateTime courseDate = ZonedDateTime.parse(dateString + "T00:00:00" + date.getOffset());

            if (!name.isEmpty() && !duration.isEmpty() && !dateString.isEmpty()) {
                CalendarActivity course = new CalendarActivity(courseDate, name, duration);
                addCourse(course);
            }
        });

        contextMenu.getItems().addAll(courseNameItem, courseDurationItem, courseDateItem, submitItem);
        contextMenu.show(stackPane, stackPane.getScene().getWindow().getX() + stackPane.getLayoutX(), stackPane.getScene().getWindow().getY() + stackPane.getLayoutY());
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> activities) {
        Map<Integer, List<CalendarActivity>> calendarMap = new HashMap<>();
        for (CalendarActivity activity : activities) {
            int day = activity.getDate().getDayOfMonth();
            calendarMap.putIfAbsent(day, new ArrayList<>());
            calendarMap.get(day).add(activity);
        }
        return calendarMap;
    }

    public ZonedDateTime getDateFocus() {
        return dateFocus;
    }
}
