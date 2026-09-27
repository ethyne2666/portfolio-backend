package com.charankumar.portfolio.lab.systemdesign.urlshortener.dto;

public class ShortenResponseDto {
    private String shortCode;
    private String originalUrl;
    private String shortUrl; // full shareable link, e.g. yoursite.com/api/lab/system-design/url-shortener/aZ3kT9

    public ShortenResponseDto(String shortCode, String originalUrl, String shortUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public String getShortCode() { return shortCode; }
    public String getOriginalUrl() { return originalUrl; }
    public String getShortUrl() { return shortUrl; }
}