package com.schegolevalex.security.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schegolevalex.unit_library.config.serdeser.PairSerializer;
import com.schegolevalex.unit_library.config.serdeser.UnitDeserializer;
import com.schegolevalex.unit_library.config.serdeser.UnitSerializer;
import com.schegolevalex.unit_library.models.units.Unit;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Module addCustomUnitDeserializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Unit.class, new UnitDeserializer());
        return simpleModule;
    }

    @Bean
    public Module addCustomUnitSerializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Unit.class, new UnitSerializer());
        return simpleModule;
    }

    @Bean
    public Module addCustomPairSerializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Pair.class, new PairSerializer());
        return simpleModule;
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
