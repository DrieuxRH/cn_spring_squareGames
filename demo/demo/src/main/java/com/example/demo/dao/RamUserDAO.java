package com.example.demo.dao;

import com.example.demo.persistance.Ram;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;


import java.util.List;
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
    public User getUserById(UUID id) {
        User correctUser = null;
        for(User user : getAllUsers()){
            if(user.getUserId() == id){
                correctUser = user;
                break;
            }
        }
        return correctUser;
    }

    @Override
    public void addUser(User user) {
        ram.getMemoire().add(user);

    }

    @Override
    public void deleteUser(UUID id) {
        for(User user : getAllUsers()){
            if(user.getUserId() == id){
                ram.getMemoire().remove(user);
            }
        }

    }
}
