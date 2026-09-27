package com.charankumar.portfolio.now.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "now_entries")
public class NowEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // what you're currently building/learning

    @Column(nullable = false)
    private boolean isCurrent = false; // only one row should be true at a time

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onSave() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCurrent() { return isCurrent; }
    public void setCurrent(boolean current) { isCurrent = current; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}