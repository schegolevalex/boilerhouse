package com.schegolevalex.heat_engineering_calculations.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    final JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, JWTUserDetailsService jwtUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
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

            String username = jwtUtil.getClaimsFromAccessToken(jwtToken).get("userName");

            // Тут происходит обращение к БД
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getUsername(),
                    userDetails.getAuthorities());

            // Может вот эту проверку вытащить наверх?
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                log.warn("Были тут*****************************");
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
