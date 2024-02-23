package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.service.GameService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

    @Autowired
    private UserDAO user;

    @GetMapping("/users")
    public List<User> getAllUsers() {return user.getAllUsers();}
}
