package com.charankumar.portfolio.lab.postman.service;

import com.charankumar.portfolio.lab.postman.dto.PlaygroundExecutionResult;

public interface LabExecutionService {
    PlaygroundExecutionResult execute(String endpointId);
}