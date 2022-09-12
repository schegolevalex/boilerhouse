package com.schegolevalex.unit_converter.configs;

import com.schegolevalex.unit_converter.controllers.converters.StringToUnitConverter;
import com.schegolevalex.unit_library.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EntityScan({"com.schegolevalex.unit_library.entities"})
public class ApplicationConfig implements WebMvcConfigurer {

    private final UnitService unitService;

    @Autowired
    public ApplicationConfig(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUnitConverter(unitService));
    }
}
