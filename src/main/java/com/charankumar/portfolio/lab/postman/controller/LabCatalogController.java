package com.charankumar.portfolio.lab.postman.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.postman.PlaygroundCatalog;
import com.charankumar.portfolio.lab.postman.PlaygroundEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lab/postman")
public class LabCatalogController {

    private final PlaygroundCatalog playgroundCatalog;

    public LabCatalogController(PlaygroundCatalog playgroundCatalog) {
        this.playgroundCatalog = playgroundCatalog;
    }

    // GET /api/lab/postman/endpoints
    // Frontend calls this ONCE to render the dropdown/menu of playable operations.
    @GetMapping("/endpoints")
    public ApiResponse<List<PlaygroundEndpoint>> getEndpoints() {
        return ApiResponse.success(playgroundCatalog.getAll());
    }
}