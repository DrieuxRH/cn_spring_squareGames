package com.example.demo.controller.dto;

import com.example.demo.user.User;

import java.util.UUID;

public class UserMapping {

    // Creates a user object from the DTO
    public User mapDtoUser(String username) {
        return new User(username, UUID.randomUUID());
    }
}
