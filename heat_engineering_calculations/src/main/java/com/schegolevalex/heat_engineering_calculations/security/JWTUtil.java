package com.schegolevalex.heat_engineering_calculations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTUtil {
    @Value("${jwt.access_token.secret}")
    private String secret;

    @Value("${jwt.access_token.expiration_time}")
    private Long accessTokenExpirationTime;

    @Value("${jwt.refresh_token.expiration_time}")
    private Long refreshTokenExpirationTime;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String userName) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("userName", userName)
                .withClaim("Expired", accessTokenExpirationTime)
                .withIssuer("www.dezone.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(accessTokenExpirationTime))
                .sign(Algorithm.HMAC256(secret));
    }

    public void validateAccessToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("www.dezone.com")
                .build();
        verifier.verify(token);
        log.info("Token {} successfully verified", token);
    }

    public Map<String, String> getClaimsFromAccessToken(String token) {
        Map<String, String> returnedMap = new HashMap<>();
        Map<String, Claim> claims = JWT.decode(token).getClaims();

        claims.forEach((key, value) -> returnedMap.put(key, value.asString()));

        return returnedMap;
    }

    public String extractAccessTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && (authHeader.startsWith("Bearer ") || authHeader.startsWith("Bearer_"))) {
            return authHeader.substring(7);
        }
        return null;
    }

    public String generateRefreshToken(String userName) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("userName", userName)
                .withIssuer("www.dezone.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(refreshTokenExpirationTime))
                .sign(Algorithm.HMAC256(secret));
    }
}
