package com.example.demo.dao;

import com.example.demo.persistance.JdbcConnection;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public User getUserById(String id) {
        User correctUser = null;
        String query = "SELECT * from users where uuid = ?";
        connection = JdbcConnection.getInstance().getConnection();
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                correctUser = getCorrectUser(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error getting users: " + e);
        }

        return correctUser;
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
            stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            int count = stmt.executeUpdate();

            if(count == 0){
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next()){
                System.out.println("LÖLLL");
                System.out.println(keys.getString(1));
                user.setUserId(UUID.fromString(keys.getString(1)));
            }

        } catch (SQLException e) {
            System.out.println("Error getting users: " + e);
        }


    }

    @Override
    public void deleteUser(String id) {

    }
}
