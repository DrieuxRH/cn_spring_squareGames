package com.example.demo.controller.dto;

import java.util.UUID;

public record UserDTO(String id,
                      String firstName,
                      String lastName,
                      String fullName,
                      String username,
                      String email,
                      String password) {

}
