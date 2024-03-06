package com.example.demo.security;

import com.example.demo.security.JwtUtil;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.user.UserAuth;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    // Replace with constructor
    @Autowired
    private JwtUtil jwUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        try {
            String accessToken = jwUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwUtil.resolveClaims(request);
            if (claims != null && jwUtil.validateClaims(claims)) {
                String username = claims.get("Username").toString();
                UserAuth userAuth = myUserDetailsService.loadUserByUsername(username);
                System.out.println("authorities: " + userAuth.getAuthorities());
                System.out.println("roles: " + userAuth.getRole());

                //TODO is the final problem, or usinf the getAuthorities() ??
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userAuth,null,
                                userAuth == null ? List.of() : userAuth.getAuthorities()
                        );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            System.out.println("payload: " + claims);


        } catch (Exception e) {
            System.out.println("Error in filte: " + e.getMessage());

        }
        filterChain.doFilter(request, response);

    }
}
