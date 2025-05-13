package com.logonedigital.worktopia.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    //TODO Generate Token
    public String generateToken(UserDetails userDetails) {
        return this.buildToken(userDetails,jwtExpiration);
    }

    private String buildToken(UserDetails userDetails, long jwtExpiration) {

        //TODO Explain BuildToken
        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        return Jwts.builder()
                .claim("authorities",authorities)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //TODO Create signing key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //TODO Extract All Claims
    private <T> T  extractAllClaims(String token, Function<Claims, T> claimsResolver ) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    //TODO Extract Username from Token
    public String extractUsername(String token) {
        return extractAllClaims(token, Claims::getSubject);
    }

    //TODO Expiration date
    private Date extractExpiration(String token) {
        return extractAllClaims(token, Claims::getExpiration);
    }

    //TODO Verify is token expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //TODO Verify valid token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
