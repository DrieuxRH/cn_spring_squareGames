package com.example.demo.controller;

import com.example.demo.controller.dto.user.*;
import com.example.demo.dao.UserDAO;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.user.User;
import com.example.demo.repository.UserRepositoryInterface;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private Optional<User> user;


    @Qualifier("mySqlUserDao")
    //@Qualifier("userRepository")
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private UserMapping userMapping;

    UserController(){
        this.userMapping = new UserMapping();
    }

    /**
    * Loads a list for all the users, and their roles.
     * Only available for the admins.
    */
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        logger.info("Info level - Get all users");
        //return ResponseHandler.generateResponse("Users found", HttpStatus.OK, new UsersDTO((List<User>) userRepositoryInterface.findAll()));
        return ResponseHandler.generateResponse("Users found", HttpStatus.OK, new UsersAuthDTO((List<UserAuthSendDTO>) myUserDetailsService.loadUserListToSend()));
    }


    /**
     *
     * @param: as pathvariable the id of the user
     * @return: info about the user
     */
    @GetMapping("/users/{userId}")
    //public UserDTO getUsersById(@PathVariable String userId) {
    public ResponseEntity<Object> getUsersById(@PathVariable String userId) {
        logger.info("Info level - Get User by id: " + userId);
        User user = userRepositoryInterface.findById(UUID.fromString(userId)).orElse(null);
        try{
            logger.info("Info - User found with the given id: " + userId);
            return ResponseHandler.generateResponse("User found", HttpStatus.OK, new UserMapping().mapUserToDto(user));

        } catch (Exception e){
            logger.error("Error level - User not found with the given id: " + userId);
            return ResponseHandler.generateResponse("User not found with the given id", HttpStatus.BAD_REQUEST, null);
        }
    }


    @PostMapping("/users")
    public String addUser(@Valid @RequestBody UserDTO params){
        User user = userMapping.mapDtoUser(params.firstName(), params.lastName(),params.username(), params.email(), params.password());
        //userDAO.addUser(user);
        System.out.println(user.toString());
        userRepositoryInterface.save(user);
        return user.getUserId().toString();
    }


    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId){
        userDAO.deleteUser(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUsername(@PathVariable String userId, @RequestBody UsernameDTO params){
        //User user = userDAO.changeUsername(params.username(), userId).orElse(null);
        User user = userRepositoryInterface.findById(UUID.fromString(userId)).orElse(null);
        user.setUsername(params.username());
        return new UserMapping().mapUserToDto(userRepositoryInterface.save(user));
    }
}
