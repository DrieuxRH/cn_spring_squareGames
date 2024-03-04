package com.example.demo.utils;

import com.example.demo.user.UserAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwUtil {

    @Value("${jwt.secret}")
    private String secret_key;
    //private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;

    public String createToken(UserAuth user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername()).build();
        //claims.put("firstName",user.getFirstName());
        //claims.put("lastName",user.getLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }
}
