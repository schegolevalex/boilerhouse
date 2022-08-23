package unit_converter.configs;

import unit_converter.controllers.converters.StringToUnitConverter;
import unit_converter.entities.relations_in_type.Relation;
import unit_converter.entities.relations_in_type.RelationInType;
import unit_converter.entities.units.*;
import unit_converter.repositories.RelationInTypeRepository;
import unit_converter.repositories.UnitRepository;
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

        unitRepository.save(new Temperature("DEGREE_CELSIUS", "°C", 1, 0, 1, 0, true));
        unitRepository.save(new Temperature("KELVIN", "K", 1, -273.15, 1, 273.15, false));
        unitRepository.save(new Temperature("DEGREE_FAHRENHEIT", "°F", 5.0 / 9, -5.0 / 9 * 32, 9 / 5.0, 32, false));

        unitRepository.save(new PowerInternationalSystem("MEGAWATT", "MW", 1, true));
        unitRepository.save(new PowerInternationalSystem("KILOWATT", "kW", 1E-3, false));
        unitRepository.save(new PowerInternationalSystem("GIGAWATT", "GW", 1E3, false));
        unitRepository.save(new PowerInternationalSystem("WATT", "W", 1E-6, false));
        unitRepository.save(new PowerInternationalSystem("VOLT-AMPERE", "VA", 1E-6, false));

        unitRepository.save(new PowerCommonUnits("GIGACALORIES_PER_HOUR", "Gcal/h", 1, true));
        unitRepository.save(new PowerCommonUnits("GIGACALORIES_PER_MINUTE", "Gcal/m", 60, false));
        unitRepository.save(new PowerCommonUnits("GIGACALORIES_PER_SECOND", "Gcal/s", 3600, false));
        unitRepository.save(new PowerCommonUnits("KILOCALORIES_PER_HOUR", "kcal/h", 1 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("KILOCALORIES_PER_MINUTE", "kcal/m", 60 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("KILOCALORIES_PER_SECOND", "kcal/s", 3600 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("CALORIES_PER_HOUR", "cal/h", 1 / 1E9, false));
        unitRepository.save(new PowerCommonUnits("CALORIES_PER_MINUTE", "cal/m", 60 / 1E9, false));
        unitRepository.save(new PowerCommonUnits("CALORIES_PER_SECOND", "cal/s", 3600 / 1E9, false));
        unitRepository.save(new PowerCommonUnits("MEGACALORIES_PER_HOUR", "Mcal/h", 1 / 1E3, false));
        unitRepository.save(new PowerCommonUnits("MEGACALORIES_PER_MINUTE", "Mcal/m", 60 / 1E3, false));
        unitRepository.save(new PowerCommonUnits("MEGACALORIES_PER_SECOND", "Mcal/s", 3600 / 1E3, false));

        unitRepository.save(new PowerCommonUnits("GIGAJOULE_PER_HOUR", "GJ/h", 0.238845897 * 1, false));
        unitRepository.save(new PowerCommonUnits("GIGAJOULE_PER_MINUTE", "GJ/m", 0.238845897 * 60, false));
        unitRepository.save(new PowerCommonUnits("GIGAJOULE_PER_SECOND", "GJ/s", 0.238845897 * 3600, false));
        unitRepository.save(new PowerCommonUnits("MEGAJOULE_PER_HOUR", "MJ/h", 0.238845897 * 1 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("MEGAJOULE_PER_MINUTE", "MJ/m", 0.238845897 * 60 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("MEGAJOULE_PER_SECOND", "MJ/s", 0.238845897 * 3600 / 1E6, false));
        unitRepository.save(new PowerCommonUnits("KILOJOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E3, false));
        unitRepository.save(new PowerCommonUnits("KILOJOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E3, false));
        unitRepository.save(new PowerCommonUnits("KILOJOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E3, false));
        unitRepository.save(new PowerCommonUnits("JOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E9, false));
        unitRepository.save(new PowerCommonUnits("JOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E9, false));
        unitRepository.save(new PowerCommonUnits("JOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E9, false));

        unitRepository.save(new FlowRateByMassMetric("TONNE_PER_HOUR", "t/h", 1.0, true));
        unitRepository.save(new FlowRateByMassMetric("TONNE_PER_MINUTE", "t/m", 1.0 * 60, false));
        unitRepository.save(new FlowRateByMassMetric("TONNE_PER_SECOND", "t/s", 1.0 * 3600, false));
        unitRepository.save(new FlowRateByMassMetric("TONNE_PER_DAY", "t/d", 1.0 / 24, false));
        unitRepository.save(new FlowRateByMassMetric("TONNE_PER_YEAR", "t/y", 1.0 / 24 / 365, false));
        unitRepository.save(new FlowRateByMassMetric("KILOGRAM_PER_HOUR", "kg/h", 1.0 / 1E3, false));
        unitRepository.save(new FlowRateByMassMetric("KILOGRAM_PER_MINUTE", "kg/m", 1.0 * 60 / 1E3, false));
        unitRepository.save(new FlowRateByMassMetric("KILOGRAM_PER_SECOND", "kg/s", 1.0 * 3600 / 1E3, false));
        unitRepository.save(new FlowRateByMassMetric("KILOGRAM_PER_DAY", "kg/d", 1.0 / 1E3 / 24, false));
        unitRepository.save(new FlowRateByMassMetric("KILOGRAM_PER_YEAR", "kg/y", 1.0 / 1E3 / 24 / 365, false));
        unitRepository.save(new FlowRateByMassMetric("GRAM_PER_HOUR", "g/h", 1.0 / 1E6, false));
        unitRepository.save(new FlowRateByMassMetric("GRAM_PER_MINUTE", "g/m", 1.0 * 60 / 1E6, false));
        unitRepository.save(new FlowRateByMassMetric("GRAM_PER_SECOND", "g/s", 1.0 * 3600 / 1E6, false));
        unitRepository.save(new FlowRateByMassMetric("GRAM_PER_DAY", "g/d", 1 / 1E6 / 24, false));
        unitRepository.save(new FlowRateByMassMetric("GRAM_PER_YEAR", "g/y", 1 / 1E6 / 24 / 365, false));


        unitRepository.save(new MassMetric("TONNE", "t", 1.0, true));
        unitRepository.save(new MassMetric("KILOGRAM", "kg", 1E3, false));
        unitRepository.save(new MassMetric("GRAM", "g", 1E6, false));
        unitRepository.save(new MassMetric("MILLIGRAM", "mg", 1E9, false));
        unitRepository.save(new MassMetric("MICROGRAM", "mcg", 1E12, false));

        relationInTypeRepository.save(new RelationInType(new Relation("distance_metric", "distance_imperial"), BigDecimal.valueOf(39.3700787), UnitType.DISTANCE));
        relationInTypeRepository.save(new RelationInType(new Relation("power_international_system", "power_common_units"), BigDecimal.valueOf(0.85984523), UnitType.POWER));

    }
}