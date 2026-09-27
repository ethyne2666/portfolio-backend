package com.charankumar.portfolio.lab.jwtplayground;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

// This is the ONLY class in the whole app that creates or reads JWTs for the playground.
// Its secret key is separate from any future "real" admin auth key — these tokens
// can never be used to access anything beyond this playground's own demo endpoint.
@Component
public class JwtUtil {

    // Generated once when the app starts. Restarting the app invalidates old demo tokens
    // (fine — this is just a playground, not a real session store).
    private final SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    private static final long EXPIRATION_MS = 5 * 60 * 1000; // demo tokens last 5 minutes

    // Creates a signed token containing the given username.
    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    // Reads a token and returns the username inside it.
    // Throws an exception automatically if the signature is invalid, or the token expired.
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // Returns ALL the readable info inside a token (used by the "decode" endpoint to show
    // the visitor exactly what's inside — issuedAt, expiration, subject, etc.)
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Simple true/false check — used by the "protected resource" endpoint.
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false; // expired, tampered with, or malformed
        }
    }
}