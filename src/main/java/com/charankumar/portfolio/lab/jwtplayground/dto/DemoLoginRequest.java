package com.charankumar.portfolio.lab.jwtplayground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// This is ALL the visitor provides — just a display name, no real password needed.
// It's a playground, not a real login system, so there's nothing to authenticate against.
public class DemoLoginRequest {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must be under 50 characters")
    private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}