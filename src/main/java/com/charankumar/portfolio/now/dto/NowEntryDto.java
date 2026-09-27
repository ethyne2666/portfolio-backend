package com.charankumar.portfolio.now.dto;

import java.time.LocalDateTime;

public class NowEntryDto {
    private String content;
    private LocalDateTime updatedAt;

    public NowEntryDto(String content, LocalDateTime updatedAt) {
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public String getContent() { return content; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}