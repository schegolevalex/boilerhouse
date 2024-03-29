package com.schegolevalex.boilerhouse.security.configs;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.schegolevalex.boilerhouse.security.models.User;
import com.schegolevalex.boilerhouse.security.exceptions.RefreshTokenVerificationException;
import com.schegolevalex.boilerhouse.security.utils.RefreshTokenUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JWTRefreshFilter extends OncePerRequestFilter {

    final RefreshTokenUtil refreshTokenUtil;

    @Autowired
    public JWTRefreshFilter(RefreshTokenUtil refreshTokenUtil) {
        this.refreshTokenUtil = refreshTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException,
            UsernameNotFoundException, JWTVerificationException {

        if (request.getRequestURI().equals("/auth/refresh")) {
            if (request.getCookies() == null)
                throw new RefreshTokenVerificationException("There is no refresh token in your request. Please, log in.");

            Cookie cookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("refresh-token"))
                    .findFirst()
                    .orElseThrow(() -> new RefreshTokenVerificationException("There is no refresh token in your request. Please, log in."));

            User userFromRefreshToken = refreshTokenUtil.getUserFromRefreshToken(cookie.getValue());

            if (userFromRefreshToken.getRefreshToken().getExpiredAt().isBefore(OffsetDateTime.now()))
                response.sendRedirect("/auth/login");
            //todo плохо так как, когда приходит пост-запрос с
            // тухлым токеном, то редирект ведет на адрес, на который доступен только GET-запрос
        }

        filterChain.doFilter(request, response);
    }
}
