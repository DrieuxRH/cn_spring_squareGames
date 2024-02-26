package com.example.demo.controller;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserMapping;
import com.example.demo.controller.dto.UsersDTO;
import com.example.demo.dao.UserDAO;
import com.example.demo.persistance.Ram;
import com.example.demo.service.GameService;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;



@RestController
public class UserController {

    private User user;

    @Autowired
    private UserDAO userDAO;

    private UserMapping userMapping;

    UserController(){
        this.userMapping = new UserMapping();
    }

    @GetMapping("/users")
    public UsersDTO getAllUsers() {return new UsersDTO(userDAO.getAllUsers());}

    @GetMapping("/users/{userId}")
    public UserDTO getUsersById(@PathVariable String userId) {
        User user = userDAO.getUserById(userId);
        System.out.println(user);
        return new UserDTO(user.getUserId().toString(), user.getUsername());
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDTO params){
        System.out.println(params);
        User user = userMapping.mapDtoUser(params.username());
        userDAO.addUser(user);
        return user.getUserId().toString();
    }
}
