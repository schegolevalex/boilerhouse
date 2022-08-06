package com.schegolevalex.boilerhouse.unit_converter.configs;

import com.schegolevalex.boilerhouse.unit_converter.controllers.converters.StringToUnitConverter;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.Relation;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.RelationInType;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceImperial;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Temperature;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.repositories.RelationInTypeRepository;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    private final UnitRepository unitRepository;
    private final RelationInTypeRepository relationInTypeRepository;

    @Autowired
    public ApplicationConfig(UnitRepository unitRepository, RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
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

        unitRepository.save(new DistanceImperial("INCH", "in", 1, true));
        unitRepository.save(new DistanceImperial("MILE", "mi", 63360, false));
        unitRepository.save(new DistanceImperial("YARD", "yd", 36, false));
        unitRepository.save(new DistanceImperial("FOOT", "ft", 12, false));

        unitRepository.save(new Temperature("KELVIN", "K", 1, 0, true));
        unitRepository.save(new Temperature("DEGREE_CELSIUS", "°C", 1, 273.15, false));
        unitRepository.save(new Temperature("DEGREE_FAHRENHEIT", "°F", 5.0/9, 5.0/9*459.67, false));

        relationInTypeRepository.save(new RelationInType(new Relation("distance_metric", "distance_imperial"), BigDecimal.valueOf(39.3700787), UnitType.DISTANCE));

    }
}