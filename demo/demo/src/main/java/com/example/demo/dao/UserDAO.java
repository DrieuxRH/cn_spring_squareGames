package com.example.demo.dao;



import com.example.demo.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(String id);
    public void addUser(User user);
    public void deleteUser(String id);

}
