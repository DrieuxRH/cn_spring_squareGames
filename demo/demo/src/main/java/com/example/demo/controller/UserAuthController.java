package com.example.demo.controller;

import com.example.demo.controller.dto.UsersAuthDTO;
import com.example.demo.controller.dto.UsersDTO;
import com.example.demo.repository.UserAuthRepository;
import com.example.demo.repository.UserRepositoryInterface;
import com.example.demo.response.ResponseHandler;
import com.example.demo.user.User;
import com.example.demo.user.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class UserAuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAuthRepository userAuthRepository;

    @GetMapping("/usersAuth")
    public ResponseEntity<Object> getAllUsers() {
        logger.info("Info level - Get all authenticaton users");
        //return new UsersDTO((List<User>) userRepositoryInterface.findAll());
        return ResponseHandler.generateResponse("Users found", HttpStatus.OK, new UsersAuthDTO((List<UserAuth>) userAuthRepository.findAll()));
    }
}
