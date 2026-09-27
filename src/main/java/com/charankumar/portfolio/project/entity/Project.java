package com.charankumar.portfolio.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This class = the "projects" table in Postgres.
// Every field here becomes a column. Hibernate creates/updates the table automatically.
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug; // used in the URL: /projects/this-slug

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String summary; // short one-liner shown in project list

    @Column(columnDefinition = "TEXT")
    private String overview; // longer description for the case study page

    @Column(columnDefinition = "TEXT")
    private String architectureNotes;

    @Column(columnDefinition = "TEXT")
    private String databaseDesign;

    @Column(columnDefinition = "TEXT")
    private String challenges;

    @Column(columnDefinition = "TEXT")
    private String solutions;

    private String githubUrl;
    private String liveUrl;
    private String coverImageUrl;

    private boolean published = false; // only published=true projects show publicly

    @ElementCollection
    @CollectionTable(name = "project_technologies", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> technologies = new ArrayList<>();

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ----- Getters and setters (Hibernate + Jackson need these) -----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getArchitectureNotes() { return architectureNotes; }
    public void setArchitectureNotes(String architectureNotes) { this.architectureNotes = architectureNotes; }

    public String getDatabaseDesign() { return databaseDesign; }
    public void setDatabaseDesign(String databaseDesign) { this.databaseDesign = databaseDesign; }

    public String getChallenges() { return challenges; }
    public void setChallenges(String challenges) { this.challenges = challenges; }

    public String getSolutions() { return solutions; }
    public void setSolutions(String solutions) { this.solutions = solutions; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String liveUrl) { this.liveUrl = liveUrl; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public List<String> getTechnologies() { return technologies; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}