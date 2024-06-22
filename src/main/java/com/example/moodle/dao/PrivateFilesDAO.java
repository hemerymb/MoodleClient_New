package com.example.moodle.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.moodle.Privatefiles.PrivateFile;


public class PrivateFilesDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insérer un fichier
            insertPrivateFile("file1.txt", 1024, "text/plain", "/path/to/file1.txt");

            // Lire tous les fichiers privés
            readPrivateFiles();

            // Mettre à jour un fichier
            updatePrivateFile(1, "file2.txt", 2048, "text/plain", "/path/to/file2.txt");

            // Lire tous les fichiers privés
            readPrivateFiles();

            // Supprimer un fichier
            deletePrivateFile(1);

            // Lire tous les fichiers privés
            readPrivateFiles();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPrivateFile(String fileName, long fileSize, String fileType, String filePath) {
        String query = "INSERT INTO private_files (fileName, fileSize, fileType, filePath) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fileName);
            statement.setLong(2, fileSize);
            statement.setString(3, fileType);
            statement.setString(4, filePath);
            statement.executeUpdate();
            System.out.println("Private file inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire tous les fichiers privés
    public static List<PrivateFile> readPrivateFiles() {
        List<PrivateFile> privateFiles = new ArrayList<>();
        String query = "SELECT * FROM private_files";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fileName = resultSet.getString("fileName");
                long fileSize = resultSet.getLong("fileSize");
                String fileType = resultSet.getString("fileType");
                String filePath = resultSet.getString("filePath");
                privateFiles.add(new PrivateFile(id, fileName, fileSize, fileType, filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privateFiles;
    }
    // Méthode pour mettre à jour un fichier privé
    public static void updatePrivateFile(int id, String fileName, long fileSize, String fileType, String filePath) {
        String query = "UPDATE private_files SET fileName = ?, fileSize = ?, fileType = ?, filePath = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fileName);
            statement.setLong(2, fileSize);
            statement.setString(3, fileType);
            statement.setString(4, filePath);
            statement.setInt(5, id);
            statement.executeUpdate();
            System.out.println("Private file updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un fichier privé
    public static void deletePrivateFile(int id) {
        String query = "DELETE FROM private_files WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Private file deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
