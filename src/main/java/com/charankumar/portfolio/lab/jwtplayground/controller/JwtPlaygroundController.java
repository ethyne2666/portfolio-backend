package com.charankumar.portfolio.lab.jwtplayground.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.lab.jwtplayground.JwtUtil;
import com.charankumar.portfolio.lab.jwtplayground.dto.DecodedTokenDto;
import com.charankumar.portfolio.lab.jwtplayground.dto.DemoLoginRequest;
import com.charankumar.portfolio.lab.jwtplayground.dto.DemoLoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab/jwt")
public class JwtPlaygroundController {

    private final JwtUtil jwtUtil;

    public JwtPlaygroundController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // POST /api/lab/jwt/demo-login
    // Body: { "username": "visitor123" }
    // No password check — this is a playground, the "login" step just demonstrates
    // that a token gets issued after some identity is provided.
    @PostMapping("/demo-login")
    public ApiResponse<DemoLoginResponse> demoLogin(@Valid @RequestBody DemoLoginRequest request) {
        String token = jwtUtil.generateToken(request.getUsername());
        DemoLoginResponse response = new DemoLoginResponse(token, request.getUsername(), 300); // 300s = 5 min
        return ApiResponse.success(response);
    }




    // GET /api/lab/jwt/decode?token=eyJhbGciOi...
    // Reads the token's contents WITHOUT caring whether it's still valid —
    // even an expired token can be decoded, since decoding just reads the payload.
    @GetMapping("/decode")
    public ApiResponse<DecodedTokenDto> decode(@RequestParam String token) {
        try {
            var claims = jwtUtil.parseClaims(token);
            DecodedTokenDto dto = new DecodedTokenDto(
                    claims.getSubject(),
                    claims.getIssuedAt(),
                    claims.getExpiration(),
                    false // if parseClaims succeeded without throwing, it's not expired
            );
            return ApiResponse.success(dto);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Special case: token signature is valid, but it's expired.
            // We can still read the claims from the exception itself.
            var claims = e.getClaims();
            DecodedTokenDto dto = new DecodedTokenDto(
                    claims.getSubject(),
                    claims.getIssuedAt(),
                    claims.getExpiration(),
                    true
            );
            return ApiResponse.success(dto);
        }
    }





    // GET /api/lab/jwt/protected-resource
    // Header required: Authorization: Bearer <token>
    // This is the "locked door" — only a valid, unexpired token gets through.
    @GetMapping("/protected-resource")
    public ApiResponse<String> protectedResource(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Case 1: no header sent at all
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new com.charankumar.portfolio.lab.jwtplayground.InvalidTokenException(
                    "Missing or malformed Authorization header. Expected: Bearer <token>"
            );
        }

        String token = authHeader.substring(7); // strip "Bearer " prefix

        // Case 2: header present, but token is invalid or expired
        if (!jwtUtil.isTokenValid(token)) {
            throw new com.charankumar.portfolio.lab.jwtplayground.InvalidTokenException(
                    "Token is invalid or expired. Please log in again."
            );
        }

        // Case 3: valid token — extract the username and let them in
        String username = jwtUtil.extractUsername(token);
        return ApiResponse.success("Welcome, " + username + "! You accessed a protected resource.");
    }
}