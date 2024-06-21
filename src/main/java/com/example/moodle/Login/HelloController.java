package com.example.moodle.Login;

import static com.example.moodle.moodleclient.Moodleclient.root;

import com.example.moodle.DBConnection;
import com.example.moodle.HelloApplication;
import com.example.moodle.MainDry.Dry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label errmsg;

    @FXML
    private Label tryconnect;

    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane pane0;

    @FXML
    public ImageView image1;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Label create;

    @FXML
    private Label message2;

    @FXML
    private Label message1;

    @FXML
    private Label signin;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField username1;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXRadioButton teacher;

    @FXML
    private JFXRadioButton teacher1;

    @FXML
    private JFXRadioButton student;

    @FXML
    private JFXRadioButton student1;

    @FXML
    private Label statut;

    @FXML
    private Label statut1;

    @FXML
    private JFXButton ButtonSignUp;

    @FXML
    private JFXButton ButtonSignUp1;

    @FXML
    private JFXButton Forget;

    @FXML
    private AnchorPane Pane2;

    @FXML
    private Label welcome;

    @FXML
    private Label sms1;

    @FXML
    private Label sms2;

    @FXML
    private Label sms3;

    @FXML
    private Label client;

    @FXML
    private Label hello;

    @FXML
    private Label sms4;

    @FXML
    private JFXButton ButonSignIn;

    @FXML
    private JFXButton ButonSignIn1;

    public static boolean isTeacher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation
        hello.setVisible(false);
        sms4.setVisible(false);
        ButonSignIn1.setVisible(false);
        ButtonSignUp.setVisible(false);
        signin.setVisible(false);
        message2.setVisible(false);
        username1.setVisible(false);
        password1.setVisible(false);
        statut1.setVisible(false);
        teacher1.setVisible(false);
        student1.setVisible(false);
        Forget.setVisible(false);
        name.setVisible(true);
        surname.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        email.setVisible(true);
        statut.setVisible(true);
        teacher.setVisible(true);
        student.setVisible(true);

        this.errmsg.setText("");
        this.errmsg.setVisible(false);
        this.tryconnect.setVisible(false);
    }

    public void btn(javafx.scene.input.MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(Pane2);

        slide.setToX(820);
        slide.play();

        Pane1.setTranslateX(-369);
        ButonSignIn1.setVisible(true);
        signin.setVisible(true);
        message2.setVisible(true);

        hello.setVisible(true);
        sms4.setVisible(true);
        ButtonSignUp.setVisible(true);
        welcome.setVisible(false);
        client.setVisible(false);
        sms1.setVisible(false);
        sms2.setVisible(false);
        sms3.setVisible(false);
        ButonSignIn.setVisible(false);
        create.setVisible(false);
        message1.setVisible(false);
        ButtonSignUp1.setVisible(false);
        username1.setVisible(true);
        password1.setVisible(true);
        statut1.setVisible(true);
        teacher1.setVisible(true);
        student1.setVisible(true);
        Forget.setVisible(true);
        name.setVisible(false);
        surname.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        email.setVisible(false);
        statut.setVisible(false);
        teacher.setVisible(false);
        student.setVisible(false);

        slide.setOnFinished((e -> {

        }));
    }

    public void btn2(javafx.scene.input.MouseEvent mouseEvent) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(Pane2);

        slide.setToX(0);
        slide.play();

        Pane1.setTranslateX(0);
        ButonSignIn1.setVisible(false);
        signin.setVisible(false);
        message2.setVisible(false);

        hello.setVisible(false);
        sms4.setVisible(false);
        ButtonSignUp.setVisible(false);
        welcome.setVisible(true);
        client.setVisible(true);
        sms1.setVisible(true);
        sms2.setVisible(true);
        sms3.setVisible(true);
        ButonSignIn.setVisible(true);
        create.setVisible(true);
        message1.setVisible(true);
        ButtonSignUp1.setVisible(true);
        username1.setVisible(false);
        password1.setVisible(false);
        statut1.setVisible(false);
        teacher1.setVisible(false);
        student1.setVisible(false);
        Forget.setVisible(false);
        name.setVisible(true);
        surname.setVisible(true);
        username.setVisible(true);
        password.setVisible(true);
        email.setVisible(true);
        statut.setVisible(true);
        teacher.setVisible(true);
        student.setVisible(true);

        slide.setOnFinished((e -> {

        }));
    }

    private boolean checkCredentials(String userName, String pass, boolean isTeacher) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ? AND statut = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                statement.setString(2, pass);
                statement.setString(3, isTeacher ? "Teacher" : "Student");

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            errmsg.setText("Error: " + e.getMessage());
            errmsg.setVisible(true);
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleSignUpBtn(ActionEvent event) {
        String firstName = name.getText();
        String lastName = surname.getText();
        String userName = username.getText();
        String pass = password.getText();
        String emailAddr = email.getText();
        boolean isTeacherSelected = teacher.isSelected();

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || pass.isEmpty() || emailAddr.isEmpty() || (!student.isSelected() && !teacher.isSelected())) {
            errmsg.setText("All fields must be filled out!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Users (name, surname, username, password, email, statut) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, userName);
                statement.setString(4, pass);
                statement.setString(5, emailAddr);
                statement.setString(6, isTeacherSelected ? "Teacher" : "Student");
                statement.executeUpdate();
                tryconnect.setVisible(true);
                tryconnect.setText("User registered successfully!");
            }
        } catch (SQLException e) {
            errmsg.setText("Error: " + e.getMessage());
            errmsg.setVisible(true);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) throws IOException {
        String userName = username1.getText();
        String pass = password1.getText();
        boolean isStudent = student1.isSelected();
        boolean isTeacher = teacher1.isSelected();

        if (userName.isEmpty() || pass.isEmpty() || (!student1.isSelected() && !teacher1.isSelected())) {
            errmsg.setText("You must enter a username, password, and select a status!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);

        if (checkCredentials(userName, pass, isTeacher)) {
            tryconnect.setText("Connected successfully!");
            tryconnect.setVisible(true);
            if (student1.isSelected()) {
                HelloController.isTeacher = false;
                root = new BorderPane();
                Dry.showDashboard(root, false);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            } else if (teacher1.isSelected()) {
                HelloController.isTeacher = true;
                root = new BorderPane();
                Dry.showDashboard(root, true);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            }
        } else {
            errmsg.setText("User does not exist!");
            errmsg.setVisible(true);
        }
    }
}