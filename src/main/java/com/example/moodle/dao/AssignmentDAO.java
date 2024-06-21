package com.example.moodle.dao;

//import com.example.moodle.Assignment;
import com.example.moodle.Teacher.AssignmentPanel.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void insertAssignment(String assignmentName, Date createdDate, Date limitedDate, String courseName, String statut) {
        String query = "INSERT INTO assignments (assignmentName, createdDate, limitedDate, courseName, statut) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, assignmentName);
            statement.setDate(2, createdDate);
            statement.setDate(3, limitedDate);
            statement.setString(4, courseName);
            statement.setString(5, statut);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Assignment> readAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM assignments";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String assignmentName = resultSet.getString("assignmentName");
                Date createdDate = resultSet.getDate("createdDate");
                Date limitedDate = resultSet.getDate("limitedDate");
                String courseName = resultSet.getString("courseName");
                String statut = resultSet.getString("statut");

                Assignment assignment = new Assignment(id, assignmentName, createdDate, limitedDate, courseName, statut);
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
