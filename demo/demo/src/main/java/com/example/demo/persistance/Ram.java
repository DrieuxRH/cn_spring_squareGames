package com.example.demo.persistance;

import com.example.demo.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ram {

    private static Ram instance;

    private HashSet<Object> memoire;


    private Ram(){
        memoire = new HashSet<Object>();
    }


    public static Ram getInstance() {
        if (instance == null) {
            instance = new Ram();
        }
        return instance;
    }

    public void addObject(Object objet){
        memoire.add(objet);
    }

    public HashSet<Object> getMemoire(){
        return memoire;
    }

    public List<User> getUsers(){
        return memoire.stream()
                .filter(o -> o.getClass().equals(User.class))
                .map(o -> (User) o)
                .toList();
    }

}
