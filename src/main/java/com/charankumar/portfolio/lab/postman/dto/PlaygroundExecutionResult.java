package com.charankumar.portfolio.lab.postman.dto;

// This is what gets sent back after "executing" a playground endpoint.
// It's exactly what a real Postman would show you: status, body, and how long it took.
public class PlaygroundExecutionResult {

    private String endpointId;
    private String method;
    private String path;
    private int statusCode;
    private Object responseBody; // whatever the real service returned (list of projects, etc.)
    private long responseTimeMs;

    public PlaygroundExecutionResult(String endpointId, String method, String path,
                                     int statusCode, Object responseBody, long responseTimeMs) {
        this.endpointId = endpointId;
        this.method = method;
        this.path = path;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.responseTimeMs = responseTimeMs;
    }

    public String getEndpointId() { return endpointId; }
    public String getMethod() { return method; }
    public String getPath() { return path; }
    public int getStatusCode() { return statusCode; }
    public Object getResponseBody() { return responseBody; }
    public long getResponseTimeMs() { return responseTimeMs; }


}