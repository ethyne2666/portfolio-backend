package com.charankumar.portfolio.lab.systemdesign.notification.dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequestDto {

    @NotBlank(message = "Message is required")
    private String message; // just a sample notification text, e.g. "New follower!"

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}