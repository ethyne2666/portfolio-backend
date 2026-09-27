package com.charankumar.portfolio.lab.systemdesign.urlshortener.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.dto.ShortenRequestDto;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.dto.ShortenResponseDto;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab/system-design/url-shortener")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    // POST /api/lab/system-design/url-shortener/shorten
    // Body: { "url": "https://example.com/some/long/path" }
    @PostMapping("/shorten")
    public ApiResponse<ShortenResponseDto> shorten(@Valid @RequestBody ShortenRequestDto request) {
        return ApiResponse.success(urlShortenerService.shorten(request.getUrl()));
    }

    // GET /api/lab/system-design/url-shortener/{code}
    // Returns the original URL as plain data (not an HTTP redirect, so the visitor
    // can SEE what it resolves to instead of instantly navigating away — better for a demo).
    @GetMapping("/{code}")
    public ApiResponse<String> resolve(@PathVariable String code) {
        return ApiResponse.success(urlShortenerService.resolve(code));
    }
}