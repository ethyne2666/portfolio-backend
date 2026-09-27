package com.charankumar.portfolio.snippet.repository;

import com.charankumar.portfolio.snippet.entity.Snippet;
import com.charankumar.portfolio.snippet.entity.SnippetLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    List<Snippet> findAllByOrderByCreatedAtDesc();
    List<Snippet> findByLanguageOrderByCreatedAtDesc(SnippetLanguage language);
}