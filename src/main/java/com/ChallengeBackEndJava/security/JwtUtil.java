package com.ChallengeBackEndJava.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mySecretKey"; // Cambia esto por una clave segura
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    // Generar token JWT
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira en 1 hora
                .sign(algorithm);
    }

    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    // Validar el token JWT
    public boolean validateToken(String token, String username) {
        try {
            JWT.require(algorithm)
                    .withSubject(username)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
