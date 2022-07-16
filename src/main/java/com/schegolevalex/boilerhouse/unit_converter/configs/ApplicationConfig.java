package com.schegolevalex.boilerhouse.unit_converter.configs;

import com.schegolevalex.boilerhouse.unit_converter.controllers.converters.StringToUnitConverter;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Temperature;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    private final UnitRepository unitRepository;

    @Autowired
    public ApplicationConfig(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUnitConverter(unitRepository));
    }

    //todo удалить отсюда всё это безобразие
    @PostConstruct
    private void addData() {
        unitRepository.save(new DistanceMetric("METER", "m", 1, true));
        unitRepository.save(new DistanceMetric("DEKAMETER", "dam", 1E1, false));
        unitRepository.save(new DistanceMetric("HECTOMETER", "hm", 1E2, false));
        unitRepository.save(new DistanceMetric("KILOMETER", "km", 1E3, false));
        unitRepository.save(new DistanceMetric("DECIMETER", "dm", 1E-1, false));
        unitRepository.save(new DistanceMetric("CENTIMETER", "cm", 1E-2, false));
        unitRepository.save(new DistanceMetric("MILLIMETER", "mm", 1E-3, false));
        unitRepository.save(new DistanceMetric("MICROMETER", "micron", 1E-6, false));
        unitRepository.save(new DistanceMetric("NANOMETER", "nm", 1E-9, false));
        unitRepository.save(new DistanceMetric("ANGSTROM", null, 1E-10, false));

        unitRepository.save(new Temperature("DEGREE_CELSIUS", "°C", 1, true));
        unitRepository.save(new Temperature("DEGREE_FAHRENHEIT", "°F", 1E1, false));
        unitRepository.save(new Temperature("KELVIN", "K", 1E2, false));
    }
}