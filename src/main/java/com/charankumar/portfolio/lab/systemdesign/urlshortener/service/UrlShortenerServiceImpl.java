package com.charankumar.portfolio.lab.systemdesign.urlshortener.service;

import com.charankumar.portfolio.common.exception.ResourceNotFoundException;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.ShortCodeGenerator;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.dto.ShortenResponseDto;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.entity.ShortUrl;
import com.charankumar.portfolio.lab.systemdesign.urlshortener.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final ShortUrlRepository shortUrlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlShortenerServiceImpl(ShortUrlRepository shortUrlRepository, ShortCodeGenerator shortCodeGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    @Override
    public ShortenResponseDto shorten(String originalUrl) {
        // Keep generating codes until we find one that's not already taken.
        // With 56 billion possible codes, this loop almost always runs exactly once.
        String code;
        do {
            code = shortCodeGenerator.generate();
        } while (shortUrlRepository.existsByShortCode(code));

        ShortUrl entity = new ShortUrl();
        entity.setOriginalUrl(originalUrl);
        entity.setShortCode(code);
        shortUrlRepository.save(entity);

        String fullShortUrl = "/api/lab/system-design/url-shortener/" + code;
        return new ShortenResponseDto(code, originalUrl, fullShortUrl);
    }

    @Override
    public String resolve(String shortCode) {
        ShortUrl entity = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short code not found: " + shortCode));

        // Increment hit count every time someone resolves this code — real, live data.
        entity.setHitCount(entity.getHitCount() + 1);
        shortUrlRepository.save(entity);

        return entity.getOriginalUrl();
    }
}