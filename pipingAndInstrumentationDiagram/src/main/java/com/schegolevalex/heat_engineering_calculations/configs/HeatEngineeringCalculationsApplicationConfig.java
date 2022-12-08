package com.schegolevalex.heat_engineering_calculations.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schegolevalex.heat_engineering_calculations.controllers.JsonArgumentResolver;
import com.schegolevalex.unit_library.config.serdeser.PairSerializer;
import com.schegolevalex.unit_library.config.serdeser.UnitDeserializer;
import com.schegolevalex.unit_library.config.serdeser.UnitSerializer;
import com.schegolevalex.unit_library.models.units.Unit;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}