package com.example.moodle.Login;

import com.example.moodle.HelloApplication;
import com.example.moodle.MainDry.Dry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.Override;

import java.net.URL;
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
    private JFXRadioButton student;

    @FXML
    private Label statut;

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

    public static BorderPane root;

    public static boolean isTeacher;

    // Ajoutez des méthodes d'initialisation si nécessaire
    @FXML
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

    @FXML
    private void handleSignUpBtn(ActionEvent event) {
        String firstName = name.getText();
        String lastName = surname.getText();
        String username = username1.getText();
        String password = password1.getText();
        String e_mail = email.getText();
        boolean isStudent = student.isSelected();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || e_mail.isEmpty() || (!student.isSelected() && !teacher.isSelected())) {
            errmsg.setText("All fields must be filled out!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);
        tryconnect.setVisible(true);
        tryconnect.setText("user register succeffully!!!");
        // Call your account creation logic here.
        System.out.println("User signed up: " + firstName + " " + lastName + ", " + username + ", " + e_mail + ", " + (isStudent ? "Student" : "Teacher"));
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) throws IOException {
        String username2 = username1.getText();
        String password2 = password1.getText();
        boolean isStudent = student.isSelected();

        if (username2.isEmpty() || password2.isEmpty() || (!student.isSelected() && !teacher.isSelected())) {
            errmsg.setText("You must enter a username, password, and select a status!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);
        // Call your login logic here.
        System.out.println("User logged in: " + username2 + ", " + (isStudent ? "Student" : "Teacher"));
        if(student.isSelected()) {
            isTeacher = false;
            root = new BorderPane();
            Dry.showDashboard(root, false);
            Scene scene = new Scene(root, 1180, 707);

            HelloApplication.stage.setTitle("Moodle Client");
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.show();
        } else if(teacher.isSelected()) {
            isTeacher = true;
            root = new BorderPane();
            Dry.showDashboard(root, true);
            Scene scene = new Scene(root, 1180, 707);

            HelloApplication.stage.setTitle("Moodle Client");
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.show();
        }



    }

}
