package com.charankumar.portfolio.snippet.service;

import com.charankumar.portfolio.snippet.dto.SnippetDto;
import com.charankumar.portfolio.snippet.entity.Snippet;
import com.charankumar.portfolio.snippet.entity.SnippetLanguage;
import com.charankumar.portfolio.snippet.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public List<SnippetDto> getAll(SnippetLanguage language) {
        List<Snippet> snippets = (language != null)
                ? snippetRepository.findByLanguageOrderByCreatedAtDesc(language)
                : snippetRepository.findAllByOrderByCreatedAtDesc();

        return snippets.stream().map(this::toDto).toList();
    }

    private SnippetDto toDto(Snippet s) {
        return new SnippetDto(s.getId(), s.getLanguage(), s.getTitle(), s.getCode(), s.getDescription());
    }
}