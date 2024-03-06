package com.example.demo.controller.dto.user;

// TODO change the role to be a list when user can have several roles
public record UserAuthSendDTO(String username, String mail, String role) {
}
