package com.schegolevalex.heat_engineering_calculations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Component
public class JWTUtil {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long expiration;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userName) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("userName", userName)
                .withIssuer("www.dezone.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(expiration))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("www.dezone.com")
                .build();

        return verifier.verify(token).getClaim("userName").asString();
    }

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
