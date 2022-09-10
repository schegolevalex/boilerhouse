package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.entities.units.*;
import com.schegolevalex.unit_library.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit getByFullName(String fullName) {
        return unitRepository.getByFullName(fullName);
    }

    public Unit getBySubtypeAndIsPrimaryIsTrue(String subType) {
        return unitRepository.getBySubtypeAndIsPrimaryIsTrue(subType);
    }

    public Optional<Unit> findById(String id) {
        return unitRepository.findById(id);
    }

    public List<Unit> findAll(Sort sort) {
        return unitRepository.findAll(sort);
    }

    private void save(Unit unit) {
        unitRepository.save(unit);
    }

    @PostConstruct
    private void init() {
        save(new DistanceMetric("METER", "m", 1, true));
        save(new DistanceMetric("DEKAMETER", "dam", 1E1, false));
        save(new DistanceMetric("HECTOMETER", "hm", 1E2, false));
        save(new DistanceMetric("KILOMETER", "km", 1E3, false));
        save(new DistanceMetric("DECIMETER", "dm", 1E-1, false));
        save(new DistanceMetric("CENTIMETER", "cm", 1E-2, false));
        save(new DistanceMetric("MILLIMETER", "mm", 1E-3, false));
        save(new DistanceMetric("MICROMETER", "micron", 1E-6, false));
        save(new DistanceMetric("NANOMETER", "nm", 1E-9, false));
        save(new DistanceMetric("ANGSTROM", null, 1E-10, false));

        save(new DistanceImperial("INCH", "in", 1, true));
        save(new DistanceImperial("MILE", "mi", 63360, false));
        save(new DistanceImperial("YARD", "yd", 36, false));
        save(new DistanceImperial("FOOT", "ft", 12, false));

        save(new Temperature("DEGREE_CELSIUS", "°C", 1, 0, 1, 0, true));
        save(new Temperature("KELVIN", "K", 1, -273.15, 1, 273.15, false));
        save(new Temperature("DEGREE_FAHRENHEIT", "°F", 5.0 / 9, -5.0 / 9 * 32, 9 / 5.0, 32, false));

        save(new PowerInternationalSystem("MEGAWATT", "MW", 1, true));
        save(new PowerInternationalSystem("KILOWATT", "kW", 1E-3, false));
        save(new PowerInternationalSystem("GIGAWATT", "GW", 1E3, false));
        save(new PowerInternationalSystem("WATT", "W", 1E-6, false));
        save(new PowerInternationalSystem("VOLT-AMPERE", "VA", 1E-6, false));

        save(new PowerCommonUnits("GIGACALORIES_PER_HOUR", "Gcal/h", 1, true));
        save(new PowerCommonUnits("GIGACALORIES_PER_MINUTE", "Gcal/m", 60, false));
        save(new PowerCommonUnits("GIGACALORIES_PER_SECOND", "Gcal/s", 3600, false));
        save(new PowerCommonUnits("KILOCALORIES_PER_HOUR", "kcal/h", 1 / 1E6, false));
        save(new PowerCommonUnits("KILOCALORIES_PER_MINUTE", "kcal/m", 60 / 1E6, false));
        save(new PowerCommonUnits("KILOCALORIES_PER_SECOND", "kcal/s", 3600 / 1E6, false));
        save(new PowerCommonUnits("CALORIES_PER_HOUR", "cal/h", 1 / 1E9, false));
        save(new PowerCommonUnits("CALORIES_PER_MINUTE", "cal/m", 60 / 1E9, false));
        save(new PowerCommonUnits("CALORIES_PER_SECOND", "cal/s", 3600 / 1E9, false));
        save(new PowerCommonUnits("MEGACALORIES_PER_HOUR", "Mcal/h", 1 / 1E3, false));
        save(new PowerCommonUnits("MEGACALORIES_PER_MINUTE", "Mcal/m", 60 / 1E3, false));
        save(new PowerCommonUnits("MEGACALORIES_PER_SECOND", "Mcal/s", 3600 / 1E3, false));

        save(new PowerCommonUnits("GIGAJOULE_PER_HOUR", "GJ/h", 0.238845897 * 1, false));
        save(new PowerCommonUnits("GIGAJOULE_PER_MINUTE", "GJ/m", 0.238845897 * 60, false));
        save(new PowerCommonUnits("GIGAJOULE_PER_SECOND", "GJ/s", 0.238845897 * 3600, false));
        save(new PowerCommonUnits("MEGAJOULE_PER_HOUR", "MJ/h", 0.238845897 * 1 / 1E6, false));
        save(new PowerCommonUnits("MEGAJOULE_PER_MINUTE", "MJ/m", 0.238845897 * 60 / 1E6, false));
        save(new PowerCommonUnits("MEGAJOULE_PER_SECOND", "MJ/s", 0.238845897 * 3600 / 1E6, false));
        save(new PowerCommonUnits("KILOJOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E3, false));
        save(new PowerCommonUnits("KILOJOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E3, false));
        save(new PowerCommonUnits("KILOJOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E3, false));
        save(new PowerCommonUnits("JOULE_PER_HOUR", "J/h", 0.238845897 * 1 / 1E9, false));
        save(new PowerCommonUnits("JOULE_PER_MINUTE", "J/m", 0.238845897 * 60 / 1E9, false));
        save(new PowerCommonUnits("JOULE_PER_SECOND", "J/s", 0.238845897 * 3600 / 1E9, false));

        save(new FlowRateByMassMetric("TONNE_PER_HOUR", "t/h", 1.0, true));
        save(new FlowRateByMassMetric("TONNE_PER_MINUTE", "t/m", 1.0 * 60, false));
        save(new FlowRateByMassMetric("TONNE_PER_SECOND", "t/s", 1.0 * 3600, false));
        save(new FlowRateByMassMetric("TONNE_PER_DAY", "t/d", 1.0 / 24, false));
        save(new FlowRateByMassMetric("TONNE_PER_YEAR", "t/y", 1.0 / 24 / 365, false));
        save(new FlowRateByMassMetric("KILOGRAM_PER_HOUR", "kg/h", 1.0 / 1E3, false));
        save(new FlowRateByMassMetric("KILOGRAM_PER_MINUTE", "kg/m", 1.0 * 60 / 1E3, false));
        save(new FlowRateByMassMetric("KILOGRAM_PER_SECOND", "kg/s", 1.0 * 3600 / 1E3, false));
        save(new FlowRateByMassMetric("KILOGRAM_PER_DAY", "kg/d", 1.0 / 1E3 / 24, false));
        save(new FlowRateByMassMetric("KILOGRAM_PER_YEAR", "kg/y", 1.0 / 1E3 / 24 / 365, false));
        save(new FlowRateByMassMetric("GRAM_PER_HOUR", "g/h", 1.0 / 1E6, false));
        save(new FlowRateByMassMetric("GRAM_PER_MINUTE", "g/m", 1.0 * 60 / 1E6, false));
        save(new FlowRateByMassMetric("GRAM_PER_SECOND", "g/s", 1.0 * 3600 / 1E6, false));
        save(new FlowRateByMassMetric("GRAM_PER_DAY", "g/d", 1 / 1E6 / 24, false));
        save(new FlowRateByMassMetric("GRAM_PER_YEAR", "g/y", 1 / 1E6 / 24 / 365, false));

        save(new FlowRateByMassImperial("POUND_PER_HOUR", "lb/h", 1.0, true));
        save(new FlowRateByMassImperial("POUND_PER_MINUTE", "lb/m", 1.0 * 60, false));
        save(new FlowRateByMassImperial("POUND_PER_SECOND", "lb/s", 1.0 * 3600, false));
        save(new FlowRateByMassImperial("POUND_PER_DAY", "lb/d", 1.0 / 24, false));
        save(new FlowRateByMassImperial("POUND_PER_YEAR", "lb/y", 1.0 / 24 / 365, false));
        save(new FlowRateByMassImperial("OUNCE_PER_HOUR", "oz/h", 0.0625, false));
        save(new FlowRateByMassImperial("OUNCE_PER_MINUTE", "oz/m", 0.0625 * 60, false));
        save(new FlowRateByMassImperial("OUNCE_PER_SECOND", "oz/s", 0.0625 * 3600, false));
        save(new FlowRateByMassImperial("OUNCE_PER_DAY", "oz/d", 0.0625 / 24, false));
        save(new FlowRateByMassImperial("OUNCE_PER_YEAR", "oz/y", 0.0625 / 24 / 365, false));

        save(new MassMetric("TONNE", "t", 1.0, true));
        save(new MassMetric("KILOGRAM", "kg", 1E3, false));
        save(new MassMetric("GRAM", "g", 1E6, false));
        save(new MassMetric("MILLIGRAM", "mg", 1E9, false));
        save(new MassMetric("MICROGRAM", "mcg", 1E12, false));
        save(new MassImperial("POUND", "lb", 1.0, true));
        save(new MassImperial("OUNCE", "oz", 0.0625, false));
    }
}
