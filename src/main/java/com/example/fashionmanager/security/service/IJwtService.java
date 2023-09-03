package com.example.fashionmanager.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJwtService {
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
    String generateToken(Map<String, Object> extraClaims, String username);
}
