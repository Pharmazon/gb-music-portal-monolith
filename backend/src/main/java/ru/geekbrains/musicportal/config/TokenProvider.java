package ru.geekbrains.musicportal.config;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.geekbrains.musicportal.entity.user.User;

import java.util.Date;

@Slf4j
@Component
public class TokenProvider{

    @Value("${musicportal.app.jwtSecret}")
    private String jwtSecret;

    @Value("${musicportal.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }

    boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: ", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: ", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: ", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: ", e);
        }

        return false;
    }
    String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
