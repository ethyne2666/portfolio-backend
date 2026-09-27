package com.charankumar.portfolio.project.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.project.dto.ProjectDetailDto;
import com.charankumar.portfolio.project.dto.ProjectSummaryDto;
import com.charankumar.portfolio.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET http://localhost:8080/api/projects
    @GetMapping
    public ApiResponse<List<ProjectSummaryDto>> getAll() {
        return ApiResponse.success(projectService.getAllPublished());
    }

    // GET http://localhost:8080/api/projects/my-slug
    @GetMapping("/{slug}")
    public ApiResponse<ProjectDetailDto> getBySlug(@PathVariable String slug) {
        return ApiResponse.success(projectService.getBySlug(slug));
    }
}