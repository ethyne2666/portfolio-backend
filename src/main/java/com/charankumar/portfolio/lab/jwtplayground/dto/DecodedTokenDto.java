package com.charankumar.portfolio.lab.jwtplayground.dto;

import java.util.Date;

// Shows the visitor exactly what's stored inside their token.
public class DecodedTokenDto {

    private String username;   // the "subject" claim
    private Date issuedAt;
    private Date expiresAt;
    private boolean expired;

    public DecodedTokenDto(String username, Date issuedAt, Date expiresAt, boolean expired) {
        this.username = username;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.expired = expired;
    }

    public String getUsername() { return username; }
    public Date getIssuedAt() { return issuedAt; }
    public Date getExpiresAt() { return expiresAt; }
    public boolean isExpired() { return expired; }
}