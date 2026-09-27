package com.charankumar.portfolio.lab.jwtplayground;

// Thrown whenever a request to a protected playground endpoint has no token,
// or a token that's invalid/expired.
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}