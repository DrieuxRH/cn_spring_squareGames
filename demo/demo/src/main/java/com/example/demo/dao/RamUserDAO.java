package com.example.demo.dao;

import com.example.demo.persistance.Ram;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RamUserDAO implements UserDAO{
    Ram ram;
    RamUserDAO(){
        ram = Ram.getInstance();
    }
    @Override
    public List<User> getAllUsers() {
        return ram.getUsers();
    }

    @Override
    public Optional<User> getUserById(String id) {
        User correctUser = null;
        System.out.println(UUID.fromString(id));
        for(User user : getAllUsers()){
            System.out.println("user id in loop: " + user.getUserId() );
            if(user.getUserId().equals(UUID.fromString(id))){
                System.out.println("found user");
                correctUser = user;
                break;
            }
        }
        return Optional.ofNullable(correctUser);
    }

    @Override
    public void addUser(User user) {
        ram.getMemoire().add(user);

    }

    @Override
    public void deleteUser(String id) {
        for(User user : getAllUsers()){
            if(user.getUserId() == UUID.fromString(id)){
                ram.getMemoire().remove(user);
            }
        }

    }

    @Override
    public Optional<User> changeUsername(String username, String id) {
        return null;
    }
}
