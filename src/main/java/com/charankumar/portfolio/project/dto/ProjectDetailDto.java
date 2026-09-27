package com.charankumar.portfolio.project.dto;

import java.util.List;

// Shape returned by GET /api/projects/{slug} (the FULL case study page)
public class ProjectDetailDto {
    private String slug;
    private String title;
    private String summary;
    private String overview;
    private String architectureNotes;
    private String databaseDesign;
    private String challenges;
    private String solutions;
    private String githubUrl;
    private String liveUrl;
    private String coverImageUrl;
    private List<String> technologies;

    public ProjectDetailDto(String slug, String title, String summary, String overview,
                            String architectureNotes, String databaseDesign, String challenges,
                            String solutions, String githubUrl, String liveUrl,
                            String coverImageUrl, List<String> technologies) {
        this.slug = slug;
        this.title = title;
        this.summary = summary;
        this.overview = overview;
        this.architectureNotes = architectureNotes;
        this.databaseDesign = databaseDesign;
        this.challenges = challenges;
        this.solutions = solutions;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
        this.coverImageUrl = coverImageUrl;
        this.technologies = technologies;
    }

    public String getSlug() { return slug; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getOverview() { return overview; }
    public String getArchitectureNotes() { return architectureNotes; }
    public String getDatabaseDesign() { return databaseDesign; }
    public String getChallenges() { return challenges; }
    public String getSolutions() { return solutions; }
    public String getGithubUrl() { return githubUrl; }
    public String getLiveUrl() { return liveUrl; }
    public String getCoverImageUrl() { return coverImageUrl; }
    public List<String> getTechnologies() { return technologies; }
}