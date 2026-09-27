package com.charankumar.portfolio.lab.postman.dto;

import jakarta.validation.constraints.NotBlank;

// This is ALL the frontend can send us. Not a URL, not a method, not a body — just an id.
// That's the entire safety mechanism in one class.
public class PlaygroundExecutionRequest {

    @NotBlank(message = "endpointId is required")
    private String endpointId;

    public String getEndpointId() { return endpointId; }
    public void setEndpointId(String endpointId) { this.endpointId = endpointId; }
}