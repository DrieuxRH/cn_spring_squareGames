package com.example.demo.service;

import com.example.demo.controller.dto.user.UserAuthSendDTO;
import com.example.demo.controller.dto.user.UsersAuthDTO;
import com.example.demo.repository.UserAuthRepositoryI;
import com.example.demo.user.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserAuthRepositoryI userAuthRepository;

    @Override
    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByUsername(username);
    }

    /**
     *
     */
    public List<UserAuthSendDTO> loadUserListToSend(){
        List<UserAuthSendDTO> users = new ArrayList<UserAuthSendDTO>();
        for(UserAuth userA : userAuthRepository.findAll() ){
            UserAuthSendDTO userDTO = new UserAuthSendDTO(userA.getUsername(), userA.getEmail(),userA.getRole().toString());
            users.add(userDTO);
        }
        return users;
    }

}
