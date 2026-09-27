package com.charankumar.portfolio.lab.postman.service;

import com.charankumar.portfolio.common.exception.ResourceNotFoundException;
import com.charankumar.portfolio.lab.postman.PlaygroundCatalog;
import com.charankumar.portfolio.lab.postman.PlaygroundEndpoint;
import com.charankumar.portfolio.lab.postman.dto.PlaygroundExecutionResult;
import com.charankumar.portfolio.now.service.NowService;
import com.charankumar.portfolio.project.service.ProjectService;
import com.charankumar.portfolio.snippet.service.SnippetService;
import org.springframework.stereotype.Service;

@Service
public class LabExecutionServiceImpl implements LabExecutionService {

    private final PlaygroundCatalog playgroundCatalog;
    private final ProjectService projectService;
    private final SnippetService snippetService;
    private final NowService nowService;

    // Spring injects all four beans automatically via this constructor
    public LabExecutionServiceImpl(PlaygroundCatalog playgroundCatalog,
                                   ProjectService projectService,
                                   SnippetService snippetService,
                                   NowService nowService) {
        this.playgroundCatalog = playgroundCatalog;
        this.projectService = projectService;
        this.snippetService = snippetService;
        this.nowService = nowService;
    }

    @Override
    public PlaygroundExecutionResult execute(String endpointId) {

        // Step 1: SAFETY CHECK — is this id actually in our approved catalog?
        // If not, we stop here. There's no path from here to "run anything the user wants."
        PlaygroundEndpoint endpoint = playgroundCatalog.findById(endpointId)
                .orElseThrow(() -> new ResourceNotFoundException("Unknown playground endpoint: " + endpointId));

        // Step 2: start the timer
        long startTime = System.currentTimeMillis();

        // Step 3: call the REAL service method that this catalog entry represents.
        // This switch is the only place that maps an id -> an actual operation.
        Object responseBody;
        int statusCode = 200;

        switch (endpointId) {
            case "get-all-projects" -> responseBody = projectService.getAllPublished();
            case "get-all-snippets" -> responseBody = snippetService.getAll(null);
            case "get-current-now" -> responseBody = nowService.getCurrent();
            default -> throw new ResourceNotFoundException("No executor implemented for: " + endpointId);
        }

        // Step 4: stop the timer
        long endTime = System.currentTimeMillis();
        long durationMs = endTime - startTime;

        return new PlaygroundExecutionResult(
                endpoint.getId(),
                endpoint.getMethod(),
                endpoint.getPath(),
                statusCode,
                responseBody,
                durationMs
        );
    }
}