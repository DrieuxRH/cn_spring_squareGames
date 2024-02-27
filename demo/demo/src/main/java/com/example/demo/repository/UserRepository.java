package com.example.demo.repository;

import com.example.demo.dao.UserDAO;
import com.example.demo.repository.UserRepositoryInterface;
import com.example.demo.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class UserRepository implements UserDAO {


    UserRepositoryInterface userRepositoryI;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (User user : userRepositoryI.findAll() ) {
            users.add(user);
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(String id) {
       /*
        System.out.println("id: " + id);
        List<User> users = getAllUsers();
        List<User> users2 = null;
        users.stream()
                .forEach(user -> System.out.println(user.getUserId()));
        users2 = users.stream()
                    .filter(user -> user.getUserId().equals(id))
                    .toList();

        System.out.println("users: " + users);
        System.out.println("users2: " + users2);

        */
        return userRepositoryI.findById(UUID.fromString(id));
    }


    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public Optional<User> changeUsername(String username, String id) {
        return null;
    }
}
