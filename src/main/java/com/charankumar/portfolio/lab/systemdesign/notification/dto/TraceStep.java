package com.charankumar.portfolio.lab.systemdesign.notification.dto;

// One stage in the simulated notification pipeline.
public class TraceStep {
    private String component;    // e.g. "API Gateway"
    private String action;       // e.g. "Received request, validated payload"
    private long durationMs;     // how long this stage took (simulated but real elapsed time)

    public TraceStep(String component, String action, long durationMs) {
        this.component = component;
        this.action = action;
        this.durationMs = durationMs;
    }

    public String getComponent() { return component; }
    public String getAction() { return action; }
    public long getDurationMs() { return durationMs; }
}