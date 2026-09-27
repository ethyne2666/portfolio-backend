package com.charankumar.portfolio.snippet.service;

import com.charankumar.portfolio.snippet.dto.SnippetDto;
import com.charankumar.portfolio.snippet.entity.SnippetLanguage;

import java.util.List;

public interface SnippetService {
    List<SnippetDto> getAll(SnippetLanguage language); // null language = return all
}