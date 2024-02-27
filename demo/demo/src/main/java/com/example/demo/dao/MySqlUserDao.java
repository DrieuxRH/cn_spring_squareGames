package com.example.demo.dao;

import com.example.demo.persistance.JdbcConnection;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MySqlUserDao implements UserDAO {

    Connection connection;
    PreparedStatement stmt = null;

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * from users";
        connection = JdbcConnection.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = getCorrectUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error getting users: " + e);
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(String id) {
        User correctUser = null;
        String query = "SELECT * from users where uuid = ?";
        connection = JdbcConnection.getInstance().getConnection();
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                correctUser = getCorrectUser(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error getting users: " + e);
        }

        return Optional.ofNullable(correctUser);
    }

    private static User getCorrectUser(ResultSet rs) throws SQLException {
        return new User(
                UUID.fromString(rs.getString("uuid")),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("mail"),
                rs.getString("username"),
                rs.getString("uuid"),
                rs.getString("password")
        );
    }


    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (username, password, mail, first_name, last_name) \n" +
                "VALUES (?,?,?,?,?)";
        connection = JdbcConnection.getInstance().getConnection();

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            int inserted = stmt.executeUpdate();

            // Getting the last inserted uuid
            System.out.println(inserted);
            stmt = connection.prepareStatement("SELECT uuid  FROM users\n" +
                                                "WHERE ts = (SELECT MAX(ts) from users);");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String id=rs.getString(1);
                System.out.println(id);
                user.setUserId(UUID.fromString(id));
            }

        } catch (SQLException e) {
            System.out.println("Error getting users: " + e);
        }
    }



    @Override
    public void deleteUser(String id) {
        String query = "DELETE FROM users WHERE uuid = ?";
        connection = JdbcConnection.getInstance().getConnection();

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting users: " + e);
        }

    }

    @Override
    public Optional<User> changeUsername(String username, String id) {
        User user = null;
        String query = "UPDATE users\n" +
                        "SET username= ?" +
                        "WHERE uuid = ?";

        connection = JdbcConnection.getInstance().getConnection();

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error modifying username: " + e);
        }

        return getUserById(id);
    }
}
