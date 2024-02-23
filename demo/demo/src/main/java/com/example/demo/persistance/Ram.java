package com.example.demo.persistance;

import com.example.demo.user.User;

import java.util.Collection;
import java.util.List;

public class Ram {

    private static Ram instance;

    private Collection<Object> memoire;


    private Ram(){
        memoire = null;
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

    public Collection getMemoire(){
        return memoire;
    }

    public List<User> getUsers(){
        return memoire.stream()
                .filter(o -> o.getClass().equals(User.class))
                .map(o -> (User) o)
                .toList();
    }

}
