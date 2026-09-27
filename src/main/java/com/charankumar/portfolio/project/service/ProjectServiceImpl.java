package com.charankumar.portfolio.project.service;

import com.charankumar.portfolio.common.exception.ResourceNotFoundException;
import com.charankumar.portfolio.project.dto.ProjectDetailDto;
import com.charankumar.portfolio.project.dto.ProjectSummaryDto;
import com.charankumar.portfolio.project.entity.Project;
import com.charankumar.portfolio.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    // Constructor injection — Spring automatically hands us the repository
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectSummaryDto> getAllPublished() {
        List<Project> projects = projectRepository.findByPublishedTrueOrderByCreatedAtDesc();
        return projects.stream().map(this::toSummaryDto).toList();
    }

    @Override
    public ProjectDetailDto getBySlug(String slug) {
        Project project = projectRepository.findBySlugAndPublishedTrue(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + slug));
        return toDetailDto(project);
    }

    // Converts entity -> summary DTO
    private ProjectSummaryDto toSummaryDto(Project p) {
        return new ProjectSummaryDto(
                p.getSlug(), p.getTitle(), p.getSummary(),
                p.getCoverImageUrl(), p.getTechnologies()
        );
    }

    // Converts entity -> detail DTO
    private ProjectDetailDto toDetailDto(Project p) {
        return new ProjectDetailDto(
                p.getSlug(), p.getTitle(), p.getSummary(), p.getOverview(),
                p.getArchitectureNotes(), p.getDatabaseDesign(), p.getChallenges(),
                p.getSolutions(), p.getGithubUrl(), p.getLiveUrl(),
                p.getCoverImageUrl(), p.getTechnologies()
        );
    }
}