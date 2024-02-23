package com.example.demo.dao;



import com.example.demo.user.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    public List<User> getAllUsers();
    public com.example.demo.user.User getUserById(UUID id);
    public void addUser(User user);
    public void deleteUser(UUID id);

}
