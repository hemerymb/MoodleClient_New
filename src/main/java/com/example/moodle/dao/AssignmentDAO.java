package com.example.moodle.dao;

import com.example.moodle.DBConnection;
import com.example.moodle.Teacher.AssignmentPanel.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    public static void insertAssignment(String assignmentName, Date createdDate, Date limitedDate, String courseName) {
        String query = "INSERT INTO assignments (assignmentName, createdDate, limitedDate, courseName) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
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
    public static void main(String[] args) {
        AssignmentDAO.insertAssignment("Test Assignment", Date.valueOf("2024-06-21"), Date.valueOf("2024-06-30"), "Test Course");
    }
}

