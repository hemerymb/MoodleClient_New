package com.example.moodle.dao;

import com.example.moodle.Student.Entities.Section;

import java.sql.*;
import java.util.ArrayList;

public class SectionDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertSection(Section section) {
        String query = "INSERT INTO section (sectionid, sectionname, courseid) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, section.getSectionid());
            statement.setString(2, section.getSectionname());
            statement.setLong(3, section.getCourseid());
            statement.executeUpdate();
            System.out.println("Section inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Section> getSections (int courseId) {
        ArrayList<Section> sections = new ArrayList<>();
        String query = "SELECT * FROM section WHERE courseid = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getInt("sectionid");
                String title = resultSet.getString("sectionname");
                int course = resultSet.getInt("courseid");
                Section section = new Section(id, title, course);
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sections;
    }
}
