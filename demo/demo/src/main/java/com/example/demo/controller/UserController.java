package com.example.demo.controller;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserMapping;
import com.example.demo.controller.dto.UsernameDTO;
import com.example.demo.controller.dto.UsersDTO;
import com.example.demo.dao.UserDAO;
import com.example.demo.user.User;
import com.example.demo.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    private Optional<User> user;


    @Qualifier("mySqlUserDao")
    //@Qualifier("userRepository")
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    private UserMapping userMapping;

    UserController(){
        this.userMapping = new UserMapping();
    }


    @GetMapping("/users")
    public UsersDTO getAllUsers() {return new UsersDTO((List<User>) userRepositoryInterface.findAll());}

    @GetMapping("/users/{userId}")
    public UserDTO getUsersById(@PathVariable String userId) {
        User user = userRepositoryInterface.findById(UUID.fromString(userId)).orElse(null);
        System.out.println(user);
        return new UserMapping().mapUserToDto(user);
    }


    @PostMapping("/users")
    public String addUser(@RequestBody UserDTO params){
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
