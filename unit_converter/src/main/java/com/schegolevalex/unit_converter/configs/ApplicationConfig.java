package com.schegolevalex.unit_converter.configs;

import com.schegolevalex.unit_converter.controllers.converters.StringToUnitConverter;
import com.schegolevalex.unit_converter.services.RelationInTypeService;
import com.schegolevalex.unit_converter.services.UnitService;
import com.schegolevalex.unit_library.entities.relations_in_type.Relation;
import com.schegolevalex.unit_library.entities.relations_in_type.RelationInType;
import com.schegolevalex.unit_library.entities.units.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Configuration
@EntityScan({"com.schegolevalex.unit_library.entities"})
public class ApplicationConfig implements WebMvcConfigurer {
    private final UnitService unitService;
    private final RelationInTypeService relationInTypeService;

    @Autowired
    public ApplicationConfig(UnitService unitService, RelationInTypeService relationInTypeService) {
        this.unitService = unitService;
        this.relationInTypeService = relationInTypeService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUnitConverter(unitService));
    }

    //todo удалить отсюда всё это безобразие
    @PostConstruct
    private void addData() {
        unitService.save(new DistanceMetric("METER", "m", 1, true));
        unitService.save(new DistanceMetric("DEKAMETER", "dam", 1E1, false));
        unitService.save(new DistanceMetric("HECTOMETER", "hm", 1E2, false));
        unitService.save(new DistanceMetric("KILOMETER", "km", 1E3, false));
        unitService.save(new DistanceMetric("DECIMETER", "dm", 1E-1, false));
        unitService.save(new DistanceMetric("CENTIMETER", "cm", 1E-2, false));
        unitService.save(new DistanceMetric("MILLIMETER", "mm", 1E-3, false));
        unitService.save(new DistanceMetric("MICROMETER", "micron", 1E-6, false));
        unitService.save(new DistanceMetric("NANOMETER", "nm", 1E-9, false));
        unitService.save(new DistanceMetric("ANGSTROM", null, 1E-10, false));

        unitService.save(new DistanceImperial("INCH", "in", 1, true));
        unitService.save(new DistanceImperial("MILE", "mi", 63360, false));
        unitService.save(new DistanceImperial("YARD", "yd", 36, false));
        unitService.save(new DistanceImperial("FOOT", "ft", 12, false));

        unitService.save(new Temperature("DEGREE_CELSIUS", "°C", 1, 0, 1, 0, true));
        unitService.save(new Temperature("KELVIN", "K", 1, -273.15, 1, 273.15, false));
        unitService.save(new Temperature("DEGREE_FAHRENHEIT", "°F", 5.0 / 9, -5.0 / 9 * 32, 9 / 5.0, 32, false));

        unitService.save(new PowerInternationalSystem("MEGAWATT", "MW", 1, true));
        unitService.save(new PowerInternationalSystem("KILOWATT", "kW", 1E-3, false));
        unitService.save(new PowerInternationalSystem("GIGAWATT", "GW", 1E3, false));
        unitService.save(new PowerInternationalSystem("WATT", "W", 1E-6, false));
        unitService.save(new PowerInternationalSystem("VOLT-AMPERE", "VA", 1E-6, false));

        unitService.save(new PowerCommonUnits("GIGACALORIES_PER_HOUR", "Gcal/h", 1, true));
        unitService.save(new PowerCommonUnits("GIGACALORIES_PER_MINUTE", "Gcal/m", 60, false));
        unitService.save(new PowerCommonUnits("GIGACALORIES_PER_SECOND", "Gcal/s", 3600, false));
        unitService.save(new PowerCommonUnits("KILOCALORIES_PER_HOUR", "kcal/h", 1 / 1E6, false));
        unitService.save(new PowerCommonUnits("KILOCALORIES_PER_MINUTE", "kcal/m", 60 / 1E6, false));
        unitService.save(new PowerCommonUnits("KILOCALORIES_PER_SECOND", "kcal/s", 3600 / 1E6, false));
        unitService.save(new PowerCommonUnits("CALORIES_PER_HOUR", "cal/h", 1 / 1E9, false));
        unitService.save(new PowerCommonUnits("CALORIES_PER_MINUTE", "cal/m", 60 / 1E9, false));
        unitService.save(new PowerCommonUnits("CALORIES_PER_SECOND", "cal/s", 3600 / 1E9, false));
        unitService.save(new PowerCommonUnits("MEGACALORIES_PER_HOUR", "Mcal/h", 1 / 1E3, false));
        unitService.save(new PowerCommonUnits("MEGACALORIES_PER_MINUTE", "Mcal/m", 60 / 1E3, false));
        unitService.save(new PowerCommonUnits("MEGACALORIES_PER_SECOND", "Mcal/s", 3600 / 1E3, false));

        unitService.save(new PowerCommonUnits("GIGAJOULE_PER_HOUR", "GJ/h", 0.238845897 * 1, false));
        unitService.save(new PowerCommonUnits("GIGAJOULE_PER_MINUTE", "GJ/m", 0.238845897 * 60, false));
        unitService.save(new PowerCommonUnits("GIGAJOULE_PER_SECOND", "GJ/s", 0.238845897 * 3600, false));
        unitService.save(new PowerCommonUnits("MEGAJOULE_PER_HOUR", "MJ/h", 0.238845897 * 1 / 1E6, false));
        unitService.save(new PowerCommonUnits("MEGAJOULE_PER_MINUTE", "MJ/m", 0.238845897 * 60 / 1E6, false));
        unitService.save(new PowerCommonUnits("MEGAJOULE_PER_SECOND", "MJ/s", 0.238845897 * 3600 / 1E6, false));
        unitService.save(new PowerCommonUnits("KILOJOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E3, false));
        unitService.save(new PowerCommonUnits("KILOJOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E3, false));
        unitService.save(new PowerCommonUnits("KILOJOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E3, false));
        unitService.save(new PowerCommonUnits("JOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E9, false));
        unitService.save(new PowerCommonUnits("JOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E9, false));
        unitService.save(new PowerCommonUnits("JOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E9, false));

        unitService.save(new FlowRateByMassMetric("TONNE_PER_HOUR", "t/h", 1.0, true));
        unitService.save(new FlowRateByMassMetric("TONNE_PER_MINUTE", "t/m", 1.0 * 60, false));
        unitService.save(new FlowRateByMassMetric("TONNE_PER_SECOND", "t/s", 1.0 * 3600, false));
        unitService.save(new FlowRateByMassMetric("TONNE_PER_DAY", "t/d", 1.0 / 24, false));
        unitService.save(new FlowRateByMassMetric("TONNE_PER_YEAR", "t/y", 1.0 / 24 / 365, false));
        unitService.save(new FlowRateByMassMetric("KILOGRAM_PER_HOUR", "kg/h", 1.0 / 1E3, false));
        unitService.save(new FlowRateByMassMetric("KILOGRAM_PER_MINUTE", "kg/m", 1.0 * 60 / 1E3, false));
        unitService.save(new FlowRateByMassMetric("KILOGRAM_PER_SECOND", "kg/s", 1.0 * 3600 / 1E3, false));
        unitService.save(new FlowRateByMassMetric("KILOGRAM_PER_DAY", "kg/d", 1.0 / 1E3 / 24, false));
        unitService.save(new FlowRateByMassMetric("KILOGRAM_PER_YEAR", "kg/y", 1.0 / 1E3 / 24 / 365, false));
        unitService.save(new FlowRateByMassMetric("GRAM_PER_HOUR", "g/h", 1.0 / 1E6, false));
        unitService.save(new FlowRateByMassMetric("GRAM_PER_MINUTE", "g/m", 1.0 * 60 / 1E6, false));
        unitService.save(new FlowRateByMassMetric("GRAM_PER_SECOND", "g/s", 1.0 * 3600 / 1E6, false));
        unitService.save(new FlowRateByMassMetric("GRAM_PER_DAY", "g/d", 1 / 1E6 / 24, false));
        unitService.save(new FlowRateByMassMetric("GRAM_PER_YEAR", "g/y", 1 / 1E6 / 24 / 365, false));

        unitService.save(new FlowRateByMassImperial("POUND_PER_HOUR", "lb/h", 1.0, true));
        unitService.save(new FlowRateByMassImperial("POUND_PER_MINUTE", "lb/m", 1.0 * 60, false));
        unitService.save(new FlowRateByMassImperial("POUND_PER_SECOND", "lb/s", 1.0 * 3600, false));
        unitService.save(new FlowRateByMassImperial("POUND_PER_DAY", "lb/d", 1.0 / 24, false));
        unitService.save(new FlowRateByMassImperial("POUND_PER_YEAR", "lb/y", 1.0 / 24 / 365, false));
        unitService.save(new FlowRateByMassImperial("OUNCE_PER_HOUR", "oz/h", 0.0625, false));
        unitService.save(new FlowRateByMassImperial("OUNCE_PER_MINUTE", "oz/m", 0.0625 * 60, false));
        unitService.save(new FlowRateByMassImperial("OUNCE_PER_SECOND", "oz/s", 0.0625 * 3600, false));
        unitService.save(new FlowRateByMassImperial("OUNCE_PER_DAY", "oz/d", 0.0625 / 24, false));
        unitService.save(new FlowRateByMassImperial("OUNCE_PER_YEAR", "oz/y", 0.0625 / 24 / 365, false));

        unitService.save(new MassMetric("TONNE", "t", 1.0, true));
        unitService.save(new MassMetric("KILOGRAM", "kg", 1E3, false));
        unitService.save(new MassMetric("GRAM", "g", 1E6, false));
        unitService.save(new MassMetric("MILLIGRAM", "mg", 1E9, false));
        unitService.save(new MassMetric("MICROGRAM", "mcg", 1E12, false));
        unitService.save(new MassImperial("POUND", "lb", 1.0, true));
        unitService.save(new MassImperial("OUNCE", "oz", 0.0625, false));


        relationInTypeService.save(new RelationInType(new Relation("distance_metric", "distance_imperial"), BigDecimal.valueOf(39.3700787), UnitType.DISTANCE));
        relationInTypeService.save(new RelationInType(new Relation("power_international_system", "power_common_units"), BigDecimal.valueOf(0.85984523), UnitType.POWER));
        relationInTypeService.save(new RelationInType(new Relation("flow_rate_by_mass_metric", "flow_rate_by_mass_imperial"), BigDecimal.valueOf(2204.62262), UnitType.FLOW_RATE_BY_MASS));
        relationInTypeService.save(new RelationInType(new Relation("mass_metric", "mass_imperial"), BigDecimal.valueOf(2204.62262), UnitType.MASS));
//        relationInTypeRepository.save(new RelationInType(new Relation("flow_rate_by_volume_metric", "flow_rate_by_volume_imperial"), BigDecimal.valueOf(), UnitType.FLOW_RATE_BY_VOLUME));


    }
}