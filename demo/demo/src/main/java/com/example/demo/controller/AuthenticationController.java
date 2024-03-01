package com.example.demo.controller;

import com.example.demo.controller.dto.AuthenticationParamsDTO;
import com.example.demo.controller.dto.UserAuthDTO;
import com.example.demo.controller.dto.UserMapping;
import com.example.demo.controller.dto.UsersAuthDTO;
import com.example.demo.repository.UserAuthRepositoryI;
import com.example.demo.response.ResponseHandler;
import com.example.demo.user.User;
import com.example.demo.user.UserAuth;
import com.example.demo.utils.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.List;

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

    @PostMapping("/api/public/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationParamsDTO params) {
        logger.info("Info level - Authenticate users");
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(params.username(), params.password());
            logger.info("Info level - Authenticate 2");
            Authentication authenticationResponse =
                    authenticationManager.authenticate(authenticationRequest);

            logger.info("Info level - Authenticate 2");
            if(authenticationResponse.isAuthenticated())             {
                System.out.println("authenticated");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

        /*//try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(params.username(), params.password()));
        //} catch (Exception e){
        //    System.out.println(e);
        //}
        System.out.println("Authentication: " );
        return ResponseHandler.generateResponse("Users found", HttpStatus.OK, null);





    }
*/
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









