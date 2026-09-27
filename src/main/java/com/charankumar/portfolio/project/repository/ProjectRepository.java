package com.charankumar.portfolio.project.repository;

import com.charankumar.portfolio.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Spring Data JPA reads these method names and auto-writes the SQL for you.
// You never implement this interface yourself — Spring generates it at runtime.
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPublishedTrueOrderByCreatedAtDesc();

    Optional<Project> findBySlugAndPublishedTrue(String slug);
}