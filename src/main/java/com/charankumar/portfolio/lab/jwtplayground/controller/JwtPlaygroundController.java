package com.charankumar.portfolio.lab.jwtplayground.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.jwtplayground.InvalidTokenException;
import com.charankumar.portfolio.lab.jwtplayground.JwtUtil;
import com.charankumar.portfolio.lab.jwtplayground.dto.DecodedTokenDto;
import com.charankumar.portfolio.lab.jwtplayground.dto.DemoLoginRequest;
import com.charankumar.portfolio.lab.jwtplayground.dto.DemoLoginResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lab/jwt")
public class JwtPlaygroundController {

    private final JwtUtil jwtUtil;

    public JwtPlaygroundController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Issues a demo JWT for the supplied username.
     * The request can optionally specify the token lifetime in seconds.
     */
    @PostMapping("/demo-login")
    public ApiResponse<DemoLoginResponse> demoLogin(
            @Valid @RequestBody DemoLoginRequest request) {

        long ttlSeconds = request.getTtlSeconds() == null
                ? 300
                : request.getTtlSeconds();

        String token = jwtUtil.generateToken(request.getUsername(), ttlSeconds);
        DemoLoginResponse response =
                new DemoLoginResponse(token, request.getUsername(), ttlSeconds);

        return ApiResponse.success(response);
    }

    /**
     * Decodes a token while still verifying its signature.
     * Expired tokens can be inspected; malformed or invalid tokens are rejected.
     */
    @GetMapping("/decode")
    public ApiResponse<DecodedTokenDto> decode(@RequestParam String token) {
        try {
            var claims = jwtUtil.parseClaims(token);

            DecodedTokenDto response = new DecodedTokenDto(
                    claims.getSubject(),
                    claims.getIssuedAt(),
                    claims.getExpiration(),
                    false
            );

            return ApiResponse.success(response);
        } catch (ExpiredJwtException exception) {
            // The signature was valid, but the token has expired.
            var claims = exception.getClaims();

            DecodedTokenDto response = new DecodedTokenDto(
                    claims.getSubject(),
                    claims.getIssuedAt(),
                    claims.getExpiration(),
                    true
            );

            return ApiResponse.success(response);
        } catch (JwtException | IllegalArgumentException exception) {
            throw new InvalidTokenException("Signature mismatch or malformed token.");
        }
    }

    /**
     * Returns a protected message only when the request contains a valid,
     * unexpired Bearer token.
     */
    @GetMapping("/protected-resource")
    public ApiResponse<String> protectedResource(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException(
                    "Missing or malformed Authorization header. Expected: Bearer <token>"
            );
        }

        String token = authHeader.substring("Bearer ".length());

        if (!jwtUtil.isTokenValid(token)) {
            throw new InvalidTokenException(
                    "Token is invalid or expired. Please log in again."
            );
        }

        String username = jwtUtil.extractUsername(token);

        return ApiResponse.success(
                "Welcome, " + username + "! You accessed a protected resource."
        );
    }
}