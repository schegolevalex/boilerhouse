package com.schegolevalex.heat_engineering_calculations.configs;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.schegolevalex.heat_engineering_calculations.security.JWTUtil;
import com.schegolevalex.heat_engineering_calculations.security.JwtUserDetailsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTFilter extends OncePerRequestFilter {

    final JWTUtil jwtUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = jwtUtil.resolveToken(request);

        if (jwtToken == null || jwtToken.isBlank())
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token in Bearer Header");

        try {
            String username = jwtUtil.validateTokenAndRetrieveClaim(jwtToken);
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getUsername(),
                    userDetails.getAuthorities());

            if (SecurityContextHolder.getContext().getAuthentication() == null)
                SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");
        }

        filterChain.doFilter(request, response);
    }
}
