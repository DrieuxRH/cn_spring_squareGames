package com.example.demo.controller;

import com.example.demo.controller.dto.AuthenticationParamsDTO;
import com.example.demo.controller.dto.UserAuthDTO;

import com.example.demo.repository.UserAuthRepositoryI;
import com.example.demo.response.ResponseHandler;

import com.example.demo.utils.JwUtil;

import com.example.demo.user.UserAuth;
import com.example.demo.utils.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthRepositoryI userAuthRepository;

    @Autowired
    private JwUtil jwUtil;




    @PostMapping("/api/public/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationParamsDTO params) {
        logger.info("Info level - Authenticate users");
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(params.username(), params.password());
            logger.info("Info level - Authenticate 2");
            Authentication authenticationResponse =
                    authenticationManager.authenticate(authenticationRequest);

            logger.info("Info level - Authenticate 3");
            if(authenticationResponse.isAuthenticated()) {
                System.out.println("authenticated");
                String token = jwUtil.createToken(authenticationResponse);
                System.out.println(token);
                responseHeaders.set("Authorization", "Bearer " + token);



            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
        //return ResponseHandler.generateResponse("User logged in with token", HttpStatus.OK,null);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("User logged in");
    };

        
    @PostMapping("usersAuth")
    public ResponseEntity<Object> createUser(@RequestBody UserAuthDTO params) {
        logger.info("Info level - Authenticate users");
        UserAuth userAuth = new UserAuth(params.username(),passwordEncoder.encode(params.password()), params.mail());

        try{
            userAuthRepository.save(userAuth);
            logger.info("Info - Create new authenticatable user " + params.username());
            return ResponseHandler.generateResponse("Users created", HttpStatus.OK, params);

        } catch (Exception e){
            logger.error("Error level - Not able to create user: " + params.username());
            return ResponseHandler.generateResponse("Not able to create user:", HttpStatus.BAD_REQUEST, null);
        }

    }


    /*
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
    }

     */
}









