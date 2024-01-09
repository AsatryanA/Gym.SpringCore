package com.epam.gym.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long accessTokenExpirationMs;
    private Set<String> blacklist = new HashSet<>();

    public String generateAccessToken(Long userId, String username) {
        log.info("generate access token");
        var claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date())
                        .getTime() + accessTokenExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getSubject(String token) {
        log.info("get subject");
        return parseJwtClaims(token).getSubject();
    }

    private Claims parseJwtClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public boolean validateJwtTokenSignature(String authToken) {
        if (isBlacklisted(authToken)) {
            throw new AccessDeniedException("Token has been revoked");
        }
        Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
        return true;
    }

    public String getToken(HttpServletRequest request) {
        var tokenHeader = "Authorization";
        var bearerToken = request.getHeader(tokenHeader);
        var tokenPrefix = "Bearer ";
        if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        }
        return null;
    }

    public Long getId(String token) {
        log.info("get id");
        Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return body.get("userId", Long.class);
    }


    public void invalidateToken(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }


}
