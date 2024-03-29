package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.security.PrivateKey;
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



    private SecretKey getPrivateSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("jwt.secret"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //private long accessTokenValidity = 60*60*1000;
    private long accessTokenValidity = 60*60;

    public String createToken(Authentication authenticationResponse) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.SECONDS.toMillis(accessTokenValidity));
        return Jwts.builder()
                //.claims().add("Username",authenticationResponse.getPrincipal().toString()).and()
                .claims().add("Username",authenticationResponse.getName()).and()
                .expiration(tokenValidity)
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
        System.out.println(Jwts.parser().verifyWith(getPrivateSigningKey()).build().parseSignedClaims(token).getPayload());
        return Jwts.parser().verifyWith(getPrivateSigningKey()).build().parseSignedClaims(token).getPayload();
    }


    public Claims resolveClaims(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            if (token != null) {
                Claims claims = parseJwtClaims(token);
                System.out.println("Expiration: " + claims.getExpiration());
                return parseJwtClaims(token);
            }
            System.out.println();
            return null;
        }catch (ExpiredJwtException ex) {
            System.out.println("Expired");
            throw ex;
        } catch (Exception ex) {
            System.out.println("Invalid");
            throw ex;
        }
    }

    public boolean validateClaims(Claims claims) {
        return claims.getExpiration().after(new Date());
    }
}
