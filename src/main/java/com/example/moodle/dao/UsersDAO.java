package com.example.moodle.dao;

import com.example.moodle.Student.Entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/moodleclient";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insérer le users
            // insertUsers("John", "Doe", "jdoe", "password123", "jdoe@example.com", "teacher");

            // Lire tous le users
            readUsers();

            // Mettre à jour le users
            updateUsers(1, "John", "Doe", "jdoe", "newpassword456", "jdoe@example.com", "student");

            // Lire tous le users
            readUsers();

            // Supprimer un users
            deleteUser(1);

            // Lire tous les enseignants
            readUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer un enseignant
    public static void insertUser(User user) {
        String query = "INSERT INTO user (userid, username, password, token, picture, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getUserid());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getToken());
            statement.setString(5, user.getPicture());
            statement.setInt(6, user.getRole());
            statement.executeUpdate();
            System.out.println("Users inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire tous les enseignants
    public static void readUsers() {
        String query = "SELECT * FROM Users";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String statut = resultSet.getString("statut");
                System.out.println("ID: " + id + ", Name: " + name + ", Surname: " + surname + ", Username: " + username +
                        ", Password: " + password + ", Email: " + email + ", Statut: " + statut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User findUser(String name) {
        User user = null;
        String query = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("userid");
                    String token = resultSet.getString("token");
                    String picture = resultSet.getString("picture");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    int role = resultSet.getInt("role");

                    user = new User(id, username, password, token, picture, role);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String token = resultSet.getString("token");
                String picture = resultSet.getString("picture");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");

                User user = new User(id, username, password, token, picture, role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Méthode pour mettre à jour un enseignant, qu'on ne va pas utiliser ici
    public static void updateUsers(int id, String name, String surname, String username, String password, String email, String statut) {
        String query = "UPDATE Users SET name = ?, surname = ?, username = ?, password = ?, email = ?, statut = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.setString(6, statut);
            statement.setInt(7, id);
            statement.executeUpdate();
            System.out.println("Users updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un enseignant
    public static void deleteUser(int id) {
        String query = "DELETE FROM user WHERE userid = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
