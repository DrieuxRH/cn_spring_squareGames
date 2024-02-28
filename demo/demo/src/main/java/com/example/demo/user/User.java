package com.example.demo.user;

import jakarta.persistence.*;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Iterator;
import java.util.UUID;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User{

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Transient
    String fullName;
    @Column(name="username")
    String username;

    @Id
    //@Basic(optional = false)
    @Column(name="uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @Column(name="mail")
    String email;

    @Column(name="password")
    String password;



    public User(UUID id, String firstName, String lastName, String fullName, String username, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = getFullName();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String fullName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = getFullName();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

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
        return firstName + " " + lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(){
        this.password = password;
    }

    @Override
    public String toString(){
        return "Username: " + getUsername() + "\n" +
                "First name: " + getFirstName() + "\n" +
                "Last Name: " + getLastName() + "\n" +
                "Id: " + getUserId();


    }
}
