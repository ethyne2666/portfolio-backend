package com.charankumar.portfolio.lab.systemdesign.notification.service;

import com.charankumar.portfolio.lab.systemdesign.notification.dto.NotificationTraceDto;

public interface NotificationSimulationService {
    NotificationTraceDto simulate(String message);
}