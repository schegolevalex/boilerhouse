package com.schegolevalex.heat_engineering_calculations.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.schegolevalex.heat_engineering_calculations.controllers.JsonArgumentResolver;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitDeserializer;
import com.schegolevalex.unit_library.entities.units.UnitSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableScheduling
@ComponentScan({"com.schegolevalex.unit_library"})
public class HeatEngineeringCalculationsApplicationConfig implements WebMvcConfigurer {

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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }
}