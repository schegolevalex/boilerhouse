package com.schegolevalex.heat_engineering_calculations.configs;

import com.schegolevalex.heat_engineering_calculations.security.JwtUserDetailsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JwtUserDetailsService jwtUserDetailsService;
    final JWTFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtUserDetailsService jwtUserDetailsService,
                          JWTFilter jwtFilter) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtFilter = jwtFilter;
    }

    //todo переписать под не-deprecated
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .
//    }
//

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/auth/login")
                .and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
