package com.charankumar.portfolio.lab.postman.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.postman.dto.PlaygroundExecutionRequest;
import com.charankumar.portfolio.lab.postman.dto.PlaygroundExecutionResult;
import com.charankumar.portfolio.lab.postman.service.LabExecutionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab/postman")
public class LabExecutionController {

    private final LabExecutionService labExecutionService;

    public LabExecutionController(LabExecutionService labExecutionService) {
        this.labExecutionService = labExecutionService;
    }

    // POST /api/lab/postman/execute
    // Body: { "endpointId": "get-all-projects" }
    @PostMapping("/execute")
    public ApiResponse<PlaygroundExecutionResult> execute(@Valid @RequestBody PlaygroundExecutionRequest request) {
        PlaygroundExecutionResult result = labExecutionService.execute(request.getEndpointId());
        return ApiResponse.success(result);
    }
}