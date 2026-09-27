package com.charankumar.portfolio.project.dto;

import java.util.List;

// Shape returned by GET /api/projects (the LIST view — lightweight, no big text fields)
public class ProjectSummaryDto {
    private String slug;
    private String title;
    private String summary;
    private String coverImageUrl;
    private List<String> technologies;

    public ProjectSummaryDto(String slug, String title, String summary,
                             String coverImageUrl, List<String> technologies) {
        this.slug = slug;
        this.title = title;
        this.summary = summary;
        this.coverImageUrl = coverImageUrl;
        this.technologies = technologies;
    }

    public String getSlug() { return slug; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getCoverImageUrl() { return coverImageUrl; }
    public List<String> getTechnologies() { return technologies; }
}