package com.charankumar.portfolio.lab.systemdesign.notification.dto;

import java.util.List;

// The full response: the ordered list of steps, plus total time.
public class NotificationTraceDto {
    private List<TraceStep> steps;
    private long totalDurationMs;

    public NotificationTraceDto(List<TraceStep> steps, long totalDurationMs) {
        this.steps = steps;
        this.totalDurationMs = totalDurationMs;
    }

    public List<TraceStep> getSteps() { return steps; }
    public long getTotalDurationMs() { return totalDurationMs; }
}