package com.example.demo.controller.dto;

import com.example.demo.user.User;

import java.util.List;

public record UsersDTO(List<User> usersList) {
}
