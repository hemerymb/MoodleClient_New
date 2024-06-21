package com.example.moodle.dao;

import java.sql.*;

public class CourseDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static void main(String[] args) {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insérer un cours
            insertCourse("Mathematics", "MATH101", "Basic Mathematics", 10, 5);

            // Lire tous les cours
            readCourses();

            // Mettre à jour un cours
            updateCourse(1, "Mathematics", "MATH102", "Advanced Mathematics", 15, 7);

            // Lire tous les cours
            readCourses();

            // Supprimer un cours
            deleteCourse(1);

            // Lire tous les cours
            readCourses();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer un cours
    public static void insertCourse(String courseName, String courseAbr, String courseDescription, int nbChapters, int nbAssignments) {
        String query = "INSERT INTO Course (courseName, courseAbr, courseDescription, nbChapters, nbAssignments) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, courseName);
            statement.setString(2, courseAbr);
            statement.setString(3, courseDescription);
            statement.setInt(4, nbChapters);
            statement.setInt(5, nbAssignments);
            statement.executeUpdate();
            System.out.println("Course inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire tous les cours
    public static void readCourses() {
        String query = "SELECT * FROM Course";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String courseName = resultSet.getString("courseName");
                String courseAbr = resultSet.getString("courseAbr");
                String courseDescription = resultSet.getString("courseDescription");
                int nbChapters = resultSet.getInt("nbChapters");
                int nbAssignments = resultSet.getInt("nbAssignments");
                System.out.println("ID: " + id + ", Course Name: " + courseName + ", Course Abbreviation: " + courseAbr +
                        ", Description: " + courseDescription + ", Chapters: " + nbChapters + ", Assignments: " + nbAssignments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un cours
    public static void updateCourse(int id, String courseName, String courseAbr, String courseDescription, int nbChapters, int nbAssignments) {
        String query = "UPDATE Course SET courseName = ?, courseAbr = ?, courseDescription = ?, nbChapters = ?, nbAssignments = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, courseName);
            statement.setString(2, courseAbr);
            statement.setString(3, courseDescription);
            statement.setInt(4, nbChapters);
            statement.setInt(5, nbAssignments);
            statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println("Course updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un cours
    public static void deleteCourse(int id) {
        String query = "DELETE FROM Course WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Course deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
