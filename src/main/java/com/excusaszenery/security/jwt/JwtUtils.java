package com.excusaszenery.security.jwt;

import com.excusaszenery.model.User;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;


    // Generar token a partir del usuario
    public String generateToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().getRoleName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener email (subject) desde el token
    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    // Validar token (firma y expiración)
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT no soportado: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("JWT malformado: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Firma inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT vacío: " + e.getMessage());
        }
        return false;
    }

    // Parsear los claims del token
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
