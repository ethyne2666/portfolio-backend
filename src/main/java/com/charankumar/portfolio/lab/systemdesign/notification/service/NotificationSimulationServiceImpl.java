package com.charankumar.portfolio.lab.systemdesign.notification.service;

import com.charankumar.portfolio.lab.systemdesign.notification.dto.NotificationTraceDto;
import com.charankumar.portfolio.lab.systemdesign.notification.dto.TraceStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationSimulationServiceImpl implements NotificationSimulationService {

    @Override
    public NotificationTraceDto simulate(String message) {
        List<TraceStep> steps = new ArrayList<>();
        long overallStart = System.currentTimeMillis();

        // Stage 1: API Gateway receives and validates the request
        steps.add(runStage("API Gateway",
                "Received request, validated payload: \"" + message + "\"", 20));

        // Stage 2: Notification Service processes business logic
        steps.add(runStage("Notification Service",
                "Determined recipient(s) and notification type", 35));

        // Stage 3: Message published to a queue (simulated — no real RabbitMQ here)
        steps.add(runStage("Message Queue",
                "Message published for asynchronous delivery", 15));

        // Stage 4: A consumer picks up the message from the queue
        steps.add(runStage("Consumer Worker",
                "Picked up message from queue for processing", 25));

        // Stage 5: Final delivery to an external provider (email/push/etc.)
        steps.add(runStage("Delivery Provider",
                "Notification dispatched to recipient", 40));

        long overallEnd = System.currentTimeMillis();
        long totalDuration = overallEnd - overallStart;

        return new NotificationTraceDto(steps, totalDuration);
    }

    // Simulates one stage taking some realistic amount of time, and records how long it ACTUALLY took.
    // We use Thread.sleep to represent "this stage would take about this long in a real system" —
    // the timing you see is real elapsed time, not a made-up number.
    private TraceStep runStage(String component, String action, long simulatedMs) {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(simulatedMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long end = System.currentTimeMillis();
        return new TraceStep(component, action, end - start);
    }
}