package com.charankumar.portfolio.snippet.dto;

import com.charankumar.portfolio.snippet.entity.SnippetLanguage;

// Single DTO is enough here — snippets are simple, no separate summary/detail needed
public class SnippetDto {
    private Long id;
    private SnippetLanguage language;
    private String title;
    private String code;
    private String description;

    public SnippetDto(Long id, SnippetLanguage language, String title, String code, String description) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.code = code;
        this.description = description;
    }

    public Long getId() { return id; }
    public SnippetLanguage getLanguage() { return language; }
    public String getTitle() { return title; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
}