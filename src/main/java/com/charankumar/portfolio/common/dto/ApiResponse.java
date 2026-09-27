package com.charankumar.portfolio.common.dto;

// This wraps EVERY successful response in the same shape.
// Frontend always gets: { success: true, data: {...}, message: null }
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        return response;
    }

    // Getters only — the frontend just reads this, nothing writes to it after creation
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public String getMessage() { return message; }
}