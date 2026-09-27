package com.charankumar.portfolio.lab.systemdesign.urlshortener.service;

import com.charankumar.portfolio.lab.systemdesign.urlshortener.dto.ShortenResponseDto;

public interface UrlShortenerService {
    ShortenResponseDto shorten(String originalUrl);
    String resolve(String shortCode); // returns the original URL, or throws if not found
}