package com.example.demo.controller.dto;

import com.example.demo.user.User;

import java.util.Optional;

public class UserMapping {

    // Creates a user object from the DTO
    public User mapDtoUser(String firstName, String lastName, String username, String email, String password) {
        return new User(firstName, lastName, firstName + " " + lastName, username, email, password);
    }

    public UserDTO mapUserToDto(User user) {
        return user != null ? new UserDTO(user.getUserId().toString(),
                            user.getFirstName(),
                            user.getLastName(),
                    user.getFirstName() + " " + user.getLastName(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getPassword()
                    ): null;
    }
}
