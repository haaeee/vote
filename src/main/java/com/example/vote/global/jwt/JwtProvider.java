package com.example.vote.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtProvider {

    public static String generateToken(final String username, final String key, final long expiredTimeMs) {
        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + expiredTimeMs);

        return Jwts.builder()
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(getSignKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static boolean isTokenExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    private static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder().setSigningKey(getSignKey(key))
                .build().parseClaimsJws(token).getBody();
    }

    public static String getUsername(String token, String key) {
        return extractClaims(token, key).get("username", String.class);
    }
}
