package com.example.moodle.dao;


import com.example.moodle.DBConnection;
import com.example.moodle.Teacher.AssignmentPanel.Assignment;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {



    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insérer un devoir
            insertAssignment("Assignment 1", Date.valueOf("2024-06-21"), Date.valueOf("2024-06-30"), "Course 1", statut);

            // Lire tous les devoirs
            readAssignments();

            // Mettre à jour un devoir
            updateAssignment(1, "Updated Assignment 1", Date.valueOf("2024-06-21"), Date.valueOf("2024-07-01"), "Course 1", "finish");

            // Lire tous les devoirs
            readAssignments();

            // Supprimer un devoir
            deleteAssignment(1);

            // Lire tous les devoirs
            readAssignments();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer un devoir
    public static void insertAssignment(String assignmentName, Date createdDate, Date limitedDate, String courseName, String statut) {
        String query = "INSERT INTO assignments (assignmentName, createdDate, limitedDate, courseName, statut) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, assignmentName);
            statement.setDate(2, createdDate);
            statement.setDate(3, limitedDate);
            statement.setString(4, courseName);
            statement.executeUpdate();
            System.out.println("Assignment inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error inserting assignment");
            e.printStackTrace();
        }
    }


    public static List<Assignment> readAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM assignments";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String assignmentName = resultSet.getString("assignmentName");
                Date createdDate = resultSet.getDate("createdDate");
                Date limitedDate = resultSet.getDate("limitedDate");
                String courseName = resultSet.getString("courseName");

                Assignment assignment = new Assignment(id, assignmentName, createdDate, limitedDate, courseName);
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            System.out.println("Error reading assignments");
            e.printStackTrace();
        }
        return assignments;

    }
    // Méthode pour mettre à jour un devoir
    public static void updateAssignment(int id, String assignmentName, Date createdDate, Date limitedDate, String courseName, String statut) {
        String query = "UPDATE assignments SET assignmentName = ?, createdDate = ?, limitedDate = ?, courseName = ?, statut = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, assignmentName);
            statement.setDate(2, createdDate);
            statement.setDate(3, limitedDate);
            statement.setString(4, courseName);
            statement.setString(5, statut);
            statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println("Assignment updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating assignment");
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un devoir
    public static void deleteAssignment(int id) {
        String query = "DELETE FROM assignments WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Assignment deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting assignment");
            e.printStackTrace();
        }
    }

}

