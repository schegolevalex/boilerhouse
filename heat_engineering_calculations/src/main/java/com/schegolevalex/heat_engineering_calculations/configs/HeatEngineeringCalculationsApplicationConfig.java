package com.schegolevalex.heat_engineering_calculations.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitDeserializer;
import com.schegolevalex.unit_library.entities.units.UnitSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
@ComponentScan({"com.schegolevalex.unit_library"})
public class HeatEngineeringCalculationsApplicationConfig implements WebMvcConfigurer {

    final MeasureFactory measureFactory;

    public HeatEngineeringCalculationsApplicationConfig(MeasureFactory measureFactory) {
        this.measureFactory = measureFactory;
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
}