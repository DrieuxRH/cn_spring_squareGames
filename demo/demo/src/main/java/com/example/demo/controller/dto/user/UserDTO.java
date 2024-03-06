package com.example.demo.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserDTO(String id,

                      @NotEmpty
                      @Size(min = 2, message = "Firstname name should have at least 2 characters")
                      String firstName,

                      @NotEmpty
                      @Size(min = 2, message = "Firstname name should have at least 2 characters")
                      String lastName,
                      String fullName,

                      @NotNull
                      String username,

                      @Email
                      @NotEmpty
                      String email,

                      @NotNull
                      String password) {

}
