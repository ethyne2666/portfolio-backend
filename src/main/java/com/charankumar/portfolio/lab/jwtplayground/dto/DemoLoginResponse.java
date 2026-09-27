package com.charankumar.portfolio.lab.jwtplayground.dto;

// What we hand back after "login" — the token itself, plus a couple of friendly details
// so the frontend can display "You are logged in as X, token expires in 5 minutes" etc.
public class DemoLoginResponse {

    private String token;
    private String username;
    private long expiresInSeconds;

    public DemoLoginResponse(String token, String username, long expiresInSeconds) {
        this.token = token;
        this.username = username;
        this.expiresInSeconds = expiresInSeconds;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public long getExpiresInSeconds() { return expiresInSeconds; }
}