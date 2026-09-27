package com.charankumar.portfolio.common.exception;

// Thrown whenever something is looked up (by slug, id, etc.) and doesn't exist.
// Example: GET /api/projects/does-not-exist -> this gets thrown
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}