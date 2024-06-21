package com.example.moodle.Teacher.CoursesPanel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.moodle.Teacher.Cards.CourseCard;
import com.example.moodle.Teacher.entity.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CoursesPanelController implements Initializable {

    @FXML
    private Label labelNewCourse;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private GridPane gridpane;

    private static ArrayList<CourseCard> list;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = new ArrayList<>();
        loadCoursesFromDatabase();

    }

    @FXML
    void handleNewCourse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateCourseDialog.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCourseToDatabase(String courseName) {
        String query = "INSERT INTO courses (name) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCourseCard(CourseCard courseCard) {

        list.add(courseCard);
        gridpane.getChildren().clear();

        int col=0, row=0;
        for (CourseCard card : list) {
            gridpane.add(card, col, row);
            col++;

            int columns = 4;//(int) Math.floor(scrollpane.getWidth() / 100);
            if (col >= columns) {
                col = 0;
                row++;
            }
        }
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/moodleclient";
        String user = "root";
        String password = "681503533";
        return DriverManager.getConnection(url, user, password);
    }

    private boolean isCourseInDatabase(String courseName) {
        String query = "SELECT COUNT(*) FROM course WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadCoursesFromDatabase() {
        String query = "SELECT * FROM Course";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                Course course = new Course(
                        rs.getString("courseName"),
                        rs.getString("courseDescription"),
                        rs.getInt("nbChapters")
                );

                addCourseCard(new CourseCard(course));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pane createCourseCard(String courseName) {
        Pane courseCard = new Pane();
        Label label = new Label(courseName);
        courseCard.getChildren().add(label);
        // Customize the course card pane as needed
        return courseCard;
    }
}
