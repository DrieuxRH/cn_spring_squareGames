package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Autowired
    private Environment env;
    //private final JwtParser jwtParser;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("jwt.secret"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtUtil(){
        //this.jwtParser = jwtParser;
        //byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("jwt.secret"));
        //SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        //this.jwtParser = Jwts.parser().verifyWith(key).build();
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

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }



    private Claims parseJwtClaims(String token) {
        System.out.println(Jwts.claims().subject(token).build());
        return Jwts.claims().subject(token).build();
    }


    public Claims resolveClaims(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            /*
            if (token != null) {

                return parseJwtClaims(token);
            }
            */
            return null;

        } catch (Exception ex) {
            request.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }
}
