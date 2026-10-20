package com.charankumar.portfolio.lab.systemdesign.notification.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.systemdesign.notification.dto.NotificationRequestDto;
import com.charankumar.portfolio.lab.systemdesign.notification.dto.NotificationTraceDto;
import com.charankumar.portfolio.lab.systemdesign.notification.service.NotificationSimulationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab/system-design/notification")
public class NotificationSimulationController {

    private final NotificationSimulationService notificationSimulationService;

    public NotificationSimulationController(NotificationSimulationService notificationSimulationService) {
        this.notificationSimulationService = notificationSimulationService;
    }

    // POST /api/lab/system-design/notification/simulate
    // Body: { "message": "New follower!" }
    @PostMapping("/simulate")
    public ApiResponse<NotificationTraceDto> simulate(@Valid @RequestBody NotificationRequestDto request) {
        return ApiResponse.success(notificationSimulationService.simulate(request.getMessage()));
    }
}