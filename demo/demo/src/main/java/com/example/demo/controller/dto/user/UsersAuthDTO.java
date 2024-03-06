package com.example.demo.controller.dto.user;

import com.example.demo.user.User;
import com.example.demo.user.UserAuth;

import java.util.List;

public record UsersAuthDTO(List<UserAuth> usersList) {

}
