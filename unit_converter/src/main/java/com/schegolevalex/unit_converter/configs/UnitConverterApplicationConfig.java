package com.schegolevalex.unit_converter.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.schegolevalex.unit_converter.controllers.converters.StringToUnitConverter;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitSerializer;
import com.schegolevalex.unit_library.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UnitConverterApplicationConfig implements WebMvcConfigurer {

    private final UnitService unitService;

    @Autowired
    public UnitConverterApplicationConfig(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUnitConverter(unitService));
    }

    @Bean
    public Module addCustomUnitSerializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Unit.class, new UnitSerializer());

        return simpleModule;
    }
}
