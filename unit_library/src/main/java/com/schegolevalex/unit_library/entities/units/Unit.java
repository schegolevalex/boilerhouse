package com.schegolevalex.unit_library.entities.units;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.schegolevalex.unit_library.entities.units.UnitType.*;

@JsonDeserialize(using = UnitDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum Unit {

    METER(DISTANCE, "distance_metric", "m", 1.0, true),
    DEKAMETER(DISTANCE, "distance_metric", "dam", 1E1, false),
    HECTOMETER(DISTANCE, "distance_metric", "hm", 1E2, false),
    KILOMETER(DISTANCE, "distance_metric", "km", 1E3, false),
    DECIMETER(DISTANCE, "distance_metric", "dm", 1E-1, false),
    CENTIMETER(DISTANCE, "distance_metric", "cm", 1E-2, false),
    MILLIMETER(DISTANCE, "distance_metric", "mm", 1E-3, false),
    MICROMETER(DISTANCE, "distance_metric", "micron", 1E-6, false),
    NANOMETER(DISTANCE, "distance_metric", "nm", 1E-9, false),
    ANGSTROM(DISTANCE, "distance_metric", null, 1E-10, false),

    INCH(DISTANCE, "distance_imperial", "in", 1.0, true),
    MILE(DISTANCE, "distance_imperial", "mi", 63360, false),
    YARD(DISTANCE, "distance_imperial", "yd", 36, false),
    FOOT(DISTANCE, "distance_imperial", "ft", 12, false),

    DEGREE_CELSIUS(TEMPERATURE, "temperature_common_units", "°C", 1.0, 0, 1.0, 0, true),
    KELVIN(TEMPERATURE, "temperature_common_units", "K", 1, -273.15, 1, 273.15, false),
    DEGREE_FAHRENHEIT(TEMPERATURE, "temperature_common_units", "°F", 5.0 / 9, -5.0 / 9 * 32, 9 / 5.0, 32, false),

    MEGAWATT(POWER, "power_international_system", "MW", 1.0, true),
    KILOWATT(POWER, "power_international_system", "kW", 1E-3, false),
    GIGAWATT(POWER, "power_international_system", "GW", 1E3, false),
    WATT(POWER, "power_international_system", "W", 1E-6, false),
    VOLT_AMPERE(POWER, "power_international_system", "VA", 1E-6, false),

    GIGACALORIES_PER_HOUR(POWER, "power_common_units", "Gcal/h", 1.0, true),
    GIGACALORIES_PER_MINUTE(POWER, "power_common_units", "Gcal/m", 60, false),
    GIGACALORIES_PER_SECOND(POWER, "power_common_units", "Gcal/s", 3600, false),
    KILOCALORIES_PER_HOUR(POWER, "power_common_units", "kcal/h", 1 / 1E6, false),
    KILOCALORIES_PER_MINUTE(POWER, "power_common_units", "kcal/m", 60 / 1E6, false),
    KILOCALORIES_PER_SECOND(POWER, "power_common_units", "kcal/s", 3600 / 1E6, false),
    CALORIES_PER_HOUR(POWER, "power_common_units", "cal/h", 1 / 1E9, false),
    CALORIES_PER_MINUTE(POWER, "power_common_units", "cal/m", 60 / 1E9, false),
    CALORIES_PER_SECOND(POWER, "power_common_units", "cal/s", 3600 / 1E9, false),
    MEGACALORIES_PER_HOUR(POWER, "power_common_units", "Mcal/h", 1 / 1E3, false),
    MEGACALORIES_PER_MINUTE(POWER, "power_common_units", "Mcal/m", 60 / 1E3, false),
    MEGACALORIES_PER_SECOND(POWER, "power_common_units", "Mcal/s", 3600 / 1E3, false),

    GIGAJOULE_PER_HOUR(POWER, "power_common_units", "GJ/h", 0.238845897 * 1, false),
    GIGAJOULE_PER_MINUTE(POWER, "power_common_units", "GJ/m", 0.238845897 * 60, false),
    GIGAJOULE_PER_SECOND(POWER, "power_common_units", "GJ/s", 0.238845897 * 3600, false),
    MEGAJOULE_PER_HOUR(POWER, "power_common_units", "MJ/h", 0.238845897 * 1 / 1E6, false),
    MEGAJOULE_PER_MINUTE(POWER, "power_common_units", "MJ/m", 0.238845897 * 60 / 1E6, false),
    MEGAJOULE_PER_SECOND(POWER, "power_common_units", "MJ/s", 0.238845897 * 3600 / 1E6, false),
    KILOJOULE_PER_HOUR(POWER, "power_common_units", "J/h", 0.238845897 * 1 / 1E3, false),
    KILOJOULE_PER_MINUTE(POWER, "power_common_units", "J/m", 0.238845897 * 60 / 1E3, false),
    KILOJOULE_PER_SECOND(POWER, "power_common_units", "J/s", 0.238845897 * 3600 / 1E3, false),
    JOULE_PER_HOUR(POWER, "power_common_units", "J/h", 0.238845897 * 1 / 1E9, false),
    JOULE_PER_MINUTE(POWER, "power_common_units", "J/m", 0.238845897 * 60 / 1E9, false),
    JOULE_PER_SECOND(POWER, "power_common_units", "J/s", 0.238845897 * 3600 / 1E9, false),

    TONNE_PER_HOUR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/h", 1.0, true),
    TONNE_PER_MINUTE(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/m", 1.0 * 60, false),
    TONNE_PER_SECOND(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/s", 1.0 * 3600, false),
    TONNE_PER_DAY(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/d", 1.0 / 24, false),
    TONNE_PER_YEAR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/y", 1.0 / 24 / 365, false),
    KILOGRAM_PER_HOUR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/h", 1.0 / 1E3, false),
    KILOGRAM_PER_MINUTE(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/m", 1.0 * 60 / 1E3, false),
    KILOGRAM_PER_SECOND(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/s", 1.0 * 3600 / 1E3, false),
    KILOGRAM_PER_DAY(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/d", 1.0 / 1E3 / 24, false),
    KILOGRAM_PER_YEAR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/y", 1.0 / 1E3 / 24 / 365, false),
    GRAM_PER_HOUR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/h", 1.0 / 1E6, false),
    GRAM_PER_MINUTE(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/m", 1.0 * 60 / 1E6, false),
    GRAM_PER_SECOND(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/s", 1.0 * 3600 / 1E6, false),
    GRAM_PER_DAY(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/d", 1 / 1E6 / 24, false),
    GRAM_PER_YEAR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/y", 1 / 1E6 / 24 / 365, false),

    POUND_PER_HOUR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/h", 1.0, true),
    POUND_PER_MINUTE(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/m", 1.0 * 60, false),
    POUND_PER_SECOND(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/s", 1.0 * 3600, false),
    POUND_PER_DAY(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/d", 1.0 / 24, false),
    POUND_PER_YEAR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/y", 1.0 / 24 / 365, false),
    OUNCE_PER_HOUR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/h", 0.0625, false),
    OUNCE_PER_MINUTE(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/m", 0.0625 * 60, false),
    OUNCE_PER_SECOND(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/s", 0.0625 * 3600, false),
    OUNCE_PER_DAY(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/d", 0.0625 / 24, false),
    OUNCE_PER_YEAR(FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/y", 0.0625 / 24 / 365, false),

    METER_3_PER_HOUR(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/h", 1.0, true),
    METER_3_PER_MINUTE(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/m", 1.0 * 60, false),
    METER_3_PER_SECOND(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/s", 1.0 * 3600, false),
    METER_3_PER_DAY(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/d", 1.0 / 24, false),
    METER_3_PER_YEAR(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/y", 1.0 / 24 / 365, false),
    LITER_PER_HOUR(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/h", 1.0 / 1E3, false),
    LITER_PER_MINUTE(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/m", 1.0 * 60 / 1E3, false),
    LITER_PER_SECOND(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/s", 1.0 * 3600 / 1E3, false),
    LITER_PER_DAY(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/d", 1.0 / 1E3 / 24, false),
    LITER_PER_YEAR(FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/y", 1.0 / 1E3 / 24 / 365, false),


    TONNE(MASS, "mass_metric", "t", 1.0, true),
    KILOGRAM(MASS, "mass_metric", "kg", 1E3, false),
    GRAM(MASS, "mass_metric", "g", 1E6, false),
    MILLIGRAM(MASS, "mass_metric", "mg", 1E9, false),
    MICROGRAM(MASS, "mass_metric", "mcg", 1E12, false),

    POUND(MASS, "mass_imperial", "lb", 1.0, true),
    OUNCE(MASS, "mass_imperial", "oz", 0.0625, false),

    ;

    String fullName;
    UnitType unitType;
    String subtype;
    String shortName;
    BigDecimal coefficient;
    Boolean isPrimary;
    BigDecimal term;
    BigDecimal coefficientFromPrimary;
    BigDecimal termFromPrimary;

    static List<Unit> UNIT_LIST = new ArrayList<>();
    static Map<String, Unit> BY_FULL_NAME = new HashMap<>();

    static {
        for (Unit u : values()) {
            UNIT_LIST.add(u);
            BY_FULL_NAME.put(u.getFullName(), u);
        }
    }

    Unit(UnitType unitType,
         String subtype,
         String shortName,
         BigDecimal coefficient,
         Boolean isPrimary) {
        this.fullName = name();
        this.unitType = unitType;
        this.subtype = subtype;
        this.shortName = shortName;
        this.coefficient = coefficient;
        this.isPrimary = isPrimary;
        this.term = null;
        this.coefficientFromPrimary = null;
        this.termFromPrimary = null;
    }

    Unit(UnitType unitType,
         String subtype,
         String shortName,
         double coefficient,
         Boolean isPrimary) {
        this(unitType,
                subtype,
                shortName,
                BigDecimal.valueOf(coefficient),
                isPrimary);
    }

    Unit(UnitType unitType,
         String subtype,
         String shortName,
         BigDecimal coefficient,
         BigDecimal term,
         BigDecimal coefficientFromPrimary,
         BigDecimal termFromPrimary,
         Boolean isPrimary
    ) {
        this.fullName = name();
        this.unitType = unitType;
        this.subtype = subtype;
        this.shortName = shortName;
        this.coefficient = coefficient;
        this.term = term;
        this.coefficientFromPrimary = coefficientFromPrimary;
        this.termFromPrimary = termFromPrimary;
        this.isPrimary = isPrimary;
    }

    Unit(UnitType unitType,
         String subtype,
         String shortName,
         double coefficient,
         double term,
         double coefficientFromPrimary,
         double termFromPrimary,
         Boolean isPrimary
    ) {
        this(unitType,
                subtype,
                shortName,
                BigDecimal.valueOf(coefficient),
                BigDecimal.valueOf(term),
                BigDecimal.valueOf(coefficientFromPrimary),
                BigDecimal.valueOf(termFromPrimary),
                isPrimary);
    }

    public static Unit valueOfFullName(String fullName) {
        Unit unit = BY_FULL_NAME.get(fullName);
        if (unit != null) return unit;
        else throw new IllegalUnitException("No such unit");

    }

    public static Unit valueOfSubtypeAndIsPrimaryIsTrue(String subType) {
        return UNIT_LIST.stream()
                .filter(u -> u.getIsPrimary() && u.getSubtype().equals(subType))
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit"));
    }

    public static List<Unit> findAll() {
        return UNIT_LIST;
    }

    @Override
    public String toString() {
        return "{unitType = " + unitType
                + ", fullName = " + fullName
                + ", shortName = " + shortName + "}";
    }
}
