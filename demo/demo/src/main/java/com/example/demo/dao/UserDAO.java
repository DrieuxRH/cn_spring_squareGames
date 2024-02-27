package com.example.demo.dao;



import com.example.demo.user.User;

import java.util.List;
import java.util.Optional;


public interface UserDAO {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id);
    public void addUser(User user);
    public void deleteUser(String id);
    public Optional<User> changeUsername(String username, String id);

}
