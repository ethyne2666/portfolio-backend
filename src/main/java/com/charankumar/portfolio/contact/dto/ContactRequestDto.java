package com.charankumar.portfolio.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// What the contact form on the frontend sends. @Valid in the controller runs these checks.
// "website" is a hidden honeypot field: real people never fill it, bots do.
public record ContactRequestDto(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be under 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 254, message = "Email is too long")
        String email,

        @NotBlank(message = "Message cannot be empty")
        @Size(max = 2000, message = "Message must be under 2000 characters")
        String message,

        String website
) {
}