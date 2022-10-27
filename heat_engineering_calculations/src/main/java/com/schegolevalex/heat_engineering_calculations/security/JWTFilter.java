package com.schegolevalex.heat_engineering_calculations.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    final JWTUtil jwtUtil;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException,
            UsernameNotFoundException, JWTVerificationException {

        String jwtToken = jwtUtil.extractAccessTokenFromRequest(request);

        if (jwtToken != null && !jwtToken.isBlank()) {
            jwtUtil.validateAccessToken(jwtToken);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                Authentication authToken = jwtUtil.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
