package com.schegolevalex.heat_engineering_calculations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String generateAccessToken(String userName, Collection<? extends GrantedAuthority> grantedAuthorities) {
        List<String> roles = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return JWT.create()
                .withSubject("User details")
                .withClaim("userName", userName)
                .withClaim("authorities", roles)
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

    public Authentication getAuthentication(String token) {
        Map<String, Claim> claimsFromAccessToken = getClaimsFromAccessToken(token);
        String username = claimsFromAccessToken.get("userName").asString();
        List<SimpleGrantedAuthority> authorities = claimsFromAccessToken.get("authorities").asList(SimpleGrantedAuthority.class);

        User principal = new User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Map<String, Claim> getClaimsFromAccessToken(String token) {
        return JWT.decode(token).getClaims();
    }
}
