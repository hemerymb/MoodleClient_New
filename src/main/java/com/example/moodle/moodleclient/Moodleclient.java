package com.example.moodle.moodleclient;

import javafx.scene.layout.BorderPane;

public class Moodleclient {
    public static BorderPane root;
    public static client_moodle client;
    public static String serverAddress = "http://localhost/";
    public static String superToken = "f1579cf1651a83cabfcc6791f43df37c";

}

class client_moodle {
    String name;
    String surname;
    String username;
    String status;
}
