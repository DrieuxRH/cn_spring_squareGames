package com.example.demo.persistance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String username;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.driver-class-name}")
    private static String driverClassName;

    @Bean
    public static DataSource source() {

        DataSourceBuilder<?> dSB = DataSourceBuilder.create();
        dSB.driverClassName("com.mysql.cj.jdbc.Driver");

        // MySQL specific url with database name
        dSB.url("jdbc:mysql://localhost:6603/square_games_spring");

        //MySQL specific url with database name
        //dSB.url(url);

        // MySQL username credential
        dSB.username("root");

        // MySQL password credential
        dSB.password("helloworld");

        return dSB.build();
    }
}
