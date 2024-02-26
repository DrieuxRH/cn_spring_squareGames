package com.example.demo.user;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

import java.util.Iterator;
import java.util.UUID;

public class User{

    String firstName;
    String lastName;
    String fullName = firstName + " " + lastName;
    String username;

    UUID id;

    public User(String username, UUID id){
        this.username = username;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUserId(){
        return id;
    }

    public void setUserId(UUID id){
        this.id = id;
    }



}
