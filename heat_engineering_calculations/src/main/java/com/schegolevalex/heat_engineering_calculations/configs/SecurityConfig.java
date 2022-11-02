package com.schegolevalex.heat_engineering_calculations.configs;

import com.schegolevalex.heat_engineering_calculations.security.JWTAccessFilter;
import com.schegolevalex.heat_engineering_calculations.security.JWTRefreshFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig {

    final JWTAccessFilter jwtAccessFilter;
    final JWTRefreshFilter jwtRefreshFilter;

    @Autowired
    public SecurityConfig(JWTAccessFilter jwtAccessFilter,
                          JWTRefreshFilter jwtRefreshFilter) {
        this.jwtAccessFilter = jwtAccessFilter;
        this.jwtRefreshFilter = jwtRefreshFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/auth/refresh").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRefreshFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
