package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JwUtil {

    @Autowired
    private Environment env;

    /*
    @Value("${jwt.secret}")
    private String secret_key;
    */

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("jwt.secret"));
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;

    public String createToken(Authentication authenticationResponse) {
        //Claims claims = Jwts.claims().add("Username",authenticationResponse.getPrincipal().toString()).build();
        //Claims claims = Jwts.claims().setSubject(authenticationResponse.getPrincipal().toString()).build();
        //claims.put("firstName",user.getFirstName());
        //claims.put("lastName",user.getLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .claims().add("Username",authenticationResponse.getPrincipal().toString()).and()
                .expiration(tokenValidity)
                //.signWith(SignatureAlgorithm.HS256, secret_key)
                .signWith(getSigningKey())
                .compact();
    }
}
