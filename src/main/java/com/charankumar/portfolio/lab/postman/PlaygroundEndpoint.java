package com.charankumar.portfolio.lab.postman;

// This describes ONE safe, pre-approved operation the visitor can "play" with.
// It is NOT a real HTTP endpoint by itself — just a description of one.
public class PlaygroundEndpoint {

    private String id;          // unique key, e.g. "get-all-projects"
    private String method;      // "GET", "POST", etc. — just for display
    private String path;        // "/api/projects" — just for display
    private String description; // human-readable explanation shown in the UI

    public PlaygroundEndpoint(String id, String method, String path, String description) {
        this.id = id;
        this.method = method;
        this.path = path;
        this.description = description;
    }

    public String getId() { return id; }
    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getDescription() { return description; }
}