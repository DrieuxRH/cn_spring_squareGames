package com.example.demo.persistance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Component
public class JdbcConnection {
    private static JdbcConnection instance;

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String username;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.driver-class-name}")
    private static String driverClassName;
    private Connection conn = null;

    private Connection createConnection() {
        // Step 1: Construct a database 'Connection' object called 'conn'
        try {
            // For MySQL only the format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:6603/square_games_spring", "root", "helloworld");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return conn;

    }

    /*
    private JdbcConnection() {
        try {
            // Get the configured datasourse
            DataSource dataSource = ConfigDataSource.source();
            // Attempt for connection to MySQL
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error in connection: " + e);
        }
    }
    */

    public static JdbcConnection getInstance() {
        if(instance == null) {
            instance = new JdbcConnection();
        }

        return instance;
    }

    private JdbcConnection(){
        conn = createConnection();
    }

    public Connection getConnection(){
        return conn;
    }
}
