package com.example.demo.response;

public class LoginResponse {
    private String username;
    private String token;

    public LoginResponse(String email, String token) {
        this.username = email;
        this.token = token;
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
