package com.example.moodle.Teacher.CoursesPanel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    private final int columns = 3; // Define the number of columns

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridpane.setVgap(10); // Set vertical gap between rows
        gridpane.setHgap(10); // Set horizontal gap between columns
        loadCoursesFromDatabase(); // Load courses from database when application starts
    }

    @FXML
    void handleNewCourse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateCourseDialog.fxml"));
            Parent root = loader.load();

            CreateCourseDialogController createCourseDialogController = loader.getController();
            createCourseDialogController.setCoursesPanelController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            String newCourseName = createCourseDialogController.getCourseName();
            if (!isCourseInDatabase(newCourseName)) {
                addCourseToDatabase(newCourseName);
                Pane courseCard = createCourseCard(newCourseName);
                addCourseCard(courseCard);
            }
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


    public void addCourseCard(Pane courseCard) {
        int count = gridpane.getChildren().size(); // Get the current number of cards
        int col = count % columns; // Calculate the column index
        int row = count / columns; // Calculate the row index
        gridpane.add(courseCard, col, row); // Add the card to the gridpane
        GridPane.setMargin(courseCard, new javafx.geometry.Insets(10)); // Adds margin between course cards
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "root";
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
        String query = "SELECT name FROM course";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String courseName = rs.getString("name");
                Pane courseCard = createCourseCard(courseName); // Assume you have a method to create a course card
                addCourseCard(courseCard);
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
