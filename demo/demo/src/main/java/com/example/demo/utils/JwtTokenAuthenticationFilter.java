package com.example.demo.utils;

import com.example.demo.user.UserAuth;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    // Replace with constructor
    @Autowired
    JwtUtil jwUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        try {
            String accessToken = jwUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                System.out.println("accessToken null");
                return;
            }
            System.out.println("token : " + accessToken);

            Claims claims = jwUtil.resolveClaims(request);
            if (claims != null && jwUtil.validateClaims(claims)) {
                System.out.println("claims valid");
                System.out.println(claims.get("Username"));
                String username = claims.get("Username").toString();
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(username,"",new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Authentication: " + authentication);
            }
            System.out.println("payload: " + claims);


        } catch (Exception e) {
            System.out.println("Error in filte: " + e.getMessage());

        }
        filterChain.doFilter(request, response);

    }
}
