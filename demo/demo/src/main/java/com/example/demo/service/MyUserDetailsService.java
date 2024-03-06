package com.example.demo.service;

import com.example.demo.repository.UserAuthRepositoryI;
import com.example.demo.user.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    //public UserDTO

}
