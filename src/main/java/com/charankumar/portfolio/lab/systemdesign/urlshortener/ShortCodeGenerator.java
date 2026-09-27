package com.charankumar.portfolio.lab.systemdesign.urlshortener;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

// Generates short, random, URL-safe codes like "aZ3kT9".
// This is a plain utility class — no database access here, just string generation.
@Component
public class ShortCodeGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();

    public String generate() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }
}