package com.example.demo.controller;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.dto.UserMapping;
import com.example.demo.controller.dto.UsernameDTO;
import com.example.demo.controller.dto.UsersDTO;
import com.example.demo.dao.MySqlUserDao;
import com.example.demo.dao.UserDAO;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private User user;


    @Qualifier("mySqlUserDao")
    @Autowired
    private UserDAO userDAO;

    private UserMapping userMapping;

    UserController(){
        this.userMapping = new UserMapping();
    }


    @GetMapping("/users")
    public UsersDTO getAllUsers() {return new UsersDTO(userDAO.getAllUsers());}

    @GetMapping("/users/{userId}")
    public UserDTO getUsersById(@PathVariable String userId) {
        User user = userDAO.getUserById(userId);
        System.out.println(user);
        return new UserMapping().mapUserToDto(user);
    }


    @PostMapping("/users")
    public String addUser(@RequestBody UserDTO params){
        System.out.println(params);
        User user = userMapping.mapDtoUser(params.firstName(), params.lastName(),params.username(), params.email(), params.password());
        userDAO.addUser(user);
        return user.getUserId().toString();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId){
        userDAO.deleteUser(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUsername(@PathVariable String userId, @RequestBody UsernameDTO params){
        user = userDAO.changeUsername(params.username(), userId);
        return new UserMapping().mapUserToDto(user);
    }
}
