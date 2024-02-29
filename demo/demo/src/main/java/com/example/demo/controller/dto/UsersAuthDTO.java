package com.example.demo.controller.dto;

import com.example.demo.user.User;
import com.example.demo.user.UserAuth;

import java.util.List;

public record UsersAuthDTO(List<UserAuth> usersList) {

}
