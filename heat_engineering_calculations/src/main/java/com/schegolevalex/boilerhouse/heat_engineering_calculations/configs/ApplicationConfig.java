package com.schegolevalex.boilerhouse.heat_engineering_calculations.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}