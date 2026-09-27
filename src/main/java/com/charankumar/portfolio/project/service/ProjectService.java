package com.charankumar.portfolio.project.service;

import com.charankumar.portfolio.project.dto.ProjectDetailDto;
import com.charankumar.portfolio.project.dto.ProjectSummaryDto;

import java.util.List;

// The "contract" — controller depends on THIS interface, not the implementation.
public interface ProjectService {
    List<ProjectSummaryDto> getAllPublished();
    ProjectDetailDto getBySlug(String slug);
}