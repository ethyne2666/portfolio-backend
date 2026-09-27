package com.charankumar.portfolio.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// This is the INCOMING shape — what the contact form on your frontend sends.
// @Valid on the controller triggers these checks automatically before the method body runs.
public class ContactRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be under 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Message cannot be empty")
    @Size(max = 2000, message = "Message must be under 2000 characters")
    private String message;

    // Getters AND setters needed here — Jackson needs setters to populate this from incoming JSON
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}