package com.schegolevalex.boilerhouse.unit_library.models.units;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalUnitException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@JsonIgnoreProperties({"coefficient", "isPrimary", "term", "coefficientFromPrimary", "termFromPrimary"})
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum Unit {

    METER(UnitType.DISTANCE, "distance_metric", "m", 1.0, true),
    DEKAMETER(UnitType.DISTANCE, "distance_metric", "dam", 1E1, false),
    HECTOMETER(UnitType.DISTANCE, "distance_metric", "hm", 1E2, false),
    KILOMETER(UnitType.DISTANCE, "distance_metric", "km", 1E3, false),
    DECIMETER(UnitType.DISTANCE, "distance_metric", "dm", 1E-1, false),
    CENTIMETER(UnitType.DISTANCE, "distance_metric", "cm", 1E-2, false),
    MILLIMETER(UnitType.DISTANCE, "distance_metric", "mm", 1E-3, false),
    MICROMETER(UnitType.DISTANCE, "distance_metric", "micron", 1E-6, false),
    NANOMETER(UnitType.DISTANCE, "distance_metric", "nm", 1E-9, false),
    ANGSTROM(UnitType.DISTANCE, "distance_metric", null, 1E-10, false),

    INCH(UnitType.DISTANCE, "distance_imperial", "in", 1.0, true),
    MILE(UnitType.DISTANCE, "distance_imperial", "mi", 63360, false),
    YARD(UnitType.DISTANCE, "distance_imperial", "yd", 36, false),
    FOOT(UnitType.DISTANCE, "distance_imperial", "ft", 12, false),

    DEGREE_CELSIUS(UnitType.TEMPERATURE, "temperature_common_units", "°C", 1.0, 0, 1.0, 0, true),
    KELVIN(UnitType.TEMPERATURE, "temperature_common_units", "K", 1, -273.15, 1, 273.15, false),
    DEGREE_FAHRENHEIT(UnitType.TEMPERATURE, "temperature_common_units", "°F", 5.0 / 9, -5.0 / 9 * 32, 9 / 5.0, 32, false),

    MEGAWATT(UnitType.POWER, "power_international_system", "MW", 1.0, true),
    KILOWATT(UnitType.POWER, "power_international_system", "kW", 1E-3, false),
    GIGAWATT(UnitType.POWER, "power_international_system", "GW", 1E3, false),
    WATT(UnitType.POWER, "power_international_system", "W", 1E-6, false),
    VOLT_AMPERE(UnitType.POWER, "power_international_system", "VA", 1E-6, false),

    GIGACALORIES_PER_HOUR(UnitType.POWER, "power_common_units", "Gcal/h", 1.0, true),
    GIGACALORIES_PER_MINUTE(UnitType.POWER, "power_common_units", "Gcal/min", 60, false),
    GIGACALORIES_PER_SECOND(UnitType.POWER, "power_common_units", "Gcal/sec", 3600, false),
    KILOCALORIES_PER_HOUR(UnitType.POWER, "power_common_units", "kcal/h", 1 / 1E6, false),
    KILOCALORIES_PER_MINUTE(UnitType.POWER, "power_common_units", "kcal/min", 60 / 1E6, false),
    KILOCALORIES_PER_SECOND(UnitType.POWER, "power_common_units", "kcal/sec", 3600 / 1E6, false),
    CALORIES_PER_HOUR(UnitType.POWER, "power_common_units", "cal/h", 1 / 1E9, false),
    CALORIES_PER_MINUTE(UnitType.POWER, "power_common_units", "cal/min", 60 / 1E9, false),
    CALORIES_PER_SECOND(UnitType.POWER, "power_common_units", "cal/sec", 3600 / 1E9, false),
    MEGACALORIES_PER_HOUR(UnitType.POWER, "power_common_units", "Mcal/h", 1 / 1E3, false),
    MEGACALORIES_PER_MINUTE(UnitType.POWER, "power_common_units", "Mcal/min", 60 / 1E3, false),
    MEGACALORIES_PER_SECOND(UnitType.POWER, "power_common_units", "Mcal/sec", 3600 / 1E3, false),

    GIGAJOULE_PER_HOUR(UnitType.POWER, "power_common_units", "GJ/h", 0.238845897 * 1, false),
    GIGAJOULE_PER_MINUTE(UnitType.POWER, "power_common_units", "GJ/min", 0.238845897 * 60, false),
    GIGAJOULE_PER_SECOND(UnitType.POWER, "power_common_units", "GJ/sec", 0.238845897 * 3600, false),
    MEGAJOULE_PER_HOUR(UnitType.POWER, "power_common_units", "MJ/h", 0.238845897 * 1 / 1E6, false),
    MEGAJOULE_PER_MINUTE(UnitType.POWER, "power_common_units", "MJ/min", 0.238845897 * 60 / 1E6, false),
    MEGAJOULE_PER_SECOND(UnitType.POWER, "power_common_units", "MJ/sec", 0.238845897 * 3600 / 1E6, false),
    KILOJOULE_PER_HOUR(UnitType.POWER, "power_common_units", "J/h", 0.238845897 * 1 / 1E3, false),
    KILOJOULE_PER_MINUTE(UnitType.POWER, "power_common_units", "J/min", 0.238845897 * 60 / 1E3, false),
    KILOJOULE_PER_SECOND(UnitType.POWER, "power_common_units", "J/sec", 0.238845897 * 3600 / 1E3, false),
    JOULE_PER_HOUR(UnitType.POWER, "power_common_units", "J/h", 0.238845897 * 1 / 1E9, false),
    JOULE_PER_MINUTE(UnitType.POWER, "power_common_units", "J/min", 0.238845897 * 60 / 1E9, false),
    JOULE_PER_SECOND(UnitType.POWER, "power_common_units", "J/sec", 0.238845897 * 3600 / 1E9, false),

    TONNE_PER_HOUR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/h", 1.0, true),
    TONNE_PER_MINUTE(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/min", 1.0 * 60, false),
    TONNE_PER_SECOND(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/sec", 1.0 * 3600, false),
    TONNE_PER_DAY(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/d", 1.0 / 24, false),
    TONNE_PER_YEAR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "t/y", 1.0 / 24 / 365, false),
    KILOGRAM_PER_HOUR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/h", 1.0 / 1E3, false),
    KILOGRAM_PER_MINUTE(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/min", 1.0 * 60 / 1E3, false),
    KILOGRAM_PER_SECOND(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/sec", 1.0 * 3600 / 1E3, false),
    KILOGRAM_PER_DAY(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/d", 1.0 / 1E3 / 24, false),
    KILOGRAM_PER_YEAR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "kg/y", 1.0 / 1E3 / 24 / 365, false),
    GRAM_PER_HOUR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/h", 1.0 / 1E6, false),
    GRAM_PER_MINUTE(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/min", 1.0 * 60 / 1E6, false),
    GRAM_PER_SECOND(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/sec", 1.0 * 3600 / 1E6, false),
    GRAM_PER_DAY(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/d", 1 / 1E6 / 24, false),
    GRAM_PER_YEAR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "g/y", 1 / 1E6 / 24 / 365, false),

    POUND_PER_HOUR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/h", 1.0, true),
    POUND_PER_MINUTE(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/min", 1.0 * 60, false),
    POUND_PER_SECOND(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/sec", 1.0 * 3600, false),
    POUND_PER_DAY(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/d", 1.0 / 24, false),
    POUND_PER_YEAR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "lb/y", 1.0 / 24 / 365, false),
    OUNCE_PER_HOUR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/h", 0.0625, false),
    OUNCE_PER_MINUTE(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/min", 0.0625 * 60, false),
    OUNCE_PER_SECOND(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/sec", 0.0625 * 3600, false),
    OUNCE_PER_DAY(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/d", 0.0625 / 24, false),
    OUNCE_PER_YEAR(UnitType.FLOW_RATE_BY_MASS, "flow_rate_by_mass_imperial", "oz/y", 0.0625 / 24 / 365, false),

    METER_3_PER_HOUR(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/h", 1.0, true),
    METER_3_PER_MINUTE(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/min", 1.0 * 60, false),
    METER_3_PER_SECOND(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/sec", 1.0 * 3600, false),
    METER_3_PER_DAY(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/d", 1.0 / 24, false),
    METER_3_PER_YEAR(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "m3/y", 1.0 / 24 / 365, false),
    LITER_PER_HOUR(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/h", 1.0 / 1E3, false),
    LITER_PER_MINUTE(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/min", 1.0 * 60 / 1E3, false),
    LITER_PER_SECOND(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/sec", 1.0 * 3600 / 1E3, false),
    LITER_PER_DAY(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/d", 1.0 / 1E3 / 24, false),
    LITER_PER_YEAR(UnitType.FLOW_RATE_BY_VOLUME, "flow_rate_by_volume_metric", "l/y", 1.0 / 1E3 / 24 / 365, false),

    TONNE(UnitType.MASS, "mass_metric", "t", 1.0, true),
    KILOGRAM(UnitType.MASS, "mass_metric", "kg", 1E3, false),
    GRAM(UnitType.MASS, "mass_metric", "g", 1E6, false),
    MILLIGRAM(UnitType.MASS, "mass_metric", "mg", 1E9, false),
    MICROGRAM(UnitType.MASS, "mass_metric", "mcg", 1E12, false),

    POUND(UnitType.MASS, "mass_imperial", "lb", 1.0, true),
    OUNCE(UnitType.MASS, "mass_imperial", "oz", 0.0625, false),

    METER_3(UnitType.VOLUME, "volume_metric", "m3", 1.0, true),
    DEKAMETER_3(UnitType.VOLUME, "volume_metric", "dm3", 1E-3, false),
    KILOMETER_3(UnitType.VOLUME, "volume_metric", "km3", 1E9, false),
    CENTIMETER_3(UnitType.VOLUME, "volume_metric", "cm3", 1E-6, false),
    MILLIMETER_3(UnitType.VOLUME, "volume_metric", "mm3", 1E-9, false),
    HECTOLITER(UnitType.VOLUME, "volume_metric", "hl", 1E-1, false),
    DECALITER(UnitType.VOLUME, "volume_metric", "dal", 1E-2, false),
    LITER(UnitType.VOLUME, "volume_metric", "l", 1E-3, false),
    DECILITER(UnitType.VOLUME, "volume_metric", "dl", 1E-4, false),
    CENTILITER(UnitType.VOLUME, "volume_metric", "cl", 1E-5, false),
    MILLILITER(UnitType.VOLUME, "volume_metric", "ml", 1E-6, false),
    MICROLITER(UnitType.VOLUME, "volume_metric", "mcl", 1E-9, false),


    METER_PER_SECOND(UnitType.SPEED, "speed_international_system", "m/sec", 1.0, true),
    DEKAMETER_PER_SECOND(UnitType.SPEED, "speed_international_system", "dm/sec", 1E1, false),
    KILOMETER_PER_SECOND(UnitType.SPEED, "speed_international_system", "km/sec", 1E3, false),
    CENTIMETER_PER_SECOND(UnitType.SPEED, "speed_international_system", "cm/sec", 1E-2, false),
    MILLIMETER_PER_SECOND(UnitType.SPEED, "speed_international_system", "mm/sec", 1E-3, false),

    METER_PER_MINUTE(UnitType.SPEED, "speed_international_system", "m/min", 1.0 / 60, false),
    DEKAMETER_PER_MINUTE(UnitType.SPEED, "speed_international_system", "dm/min", 1E1 / 60, false),
    KILOMETER_PER_MINUTE(UnitType.SPEED, "speed_international_system", "km/min", 1E3 / 60, false),
    CENTIMETER_PER_MINUTE(UnitType.SPEED, "speed_international_system", "cm/min", 1E-2 / 60, false),
    MILLIMETER_PER_MINUTE(UnitType.SPEED, "speed_international_system", "mm/min", 1E-3 / 60, false),

    METER_PER_HOUR(UnitType.SPEED, "speed_international_system", "m/h", 1.0 / 60 / 60, false),
    DEKAMETER_PER_HOUR(UnitType.SPEED, "speed_international_system", "dm/h", 1E1 / 60 / 60, false),
    KILOMETER_PER_HOUR(UnitType.SPEED, "speed_international_system", "km/h", 1E3 / 60 / 60, false),
    CENTIMETER_PER_HOUR(UnitType.SPEED, "speed_international_system", "cm/h", 1E-2 / 60 / 60, false),
    MILLIMETER_PER_HOUR(UnitType.SPEED, "speed_international_system", "mm/h", 1E-3 / 60 / 60, false),

    METER_PER_DAY(UnitType.SPEED, "speed_international_system", "m/d", 1.0 / 60 / 60 / 24, false),
    DEKAMETER_PER_DAY(UnitType.SPEED, "speed_international_system", "dm/d", 1E1 / 60 / 60 / 24, false),
    KILOMETER_PER_DAY(UnitType.SPEED, "speed_international_system", "km/d", 1E3 / 60 / 60 / 24, false),
    CENTIMETER_PER_DAY(UnitType.SPEED, "speed_international_system", "cm/d", 1E-2 / 60 / 60 / 24, false),
    MILLIMETER_PER_DAY(UnitType.SPEED, "speed_international_system", "mm/d", 1E-3 / 60 / 60 / 24, false),

    METER_PER_YEAR(UnitType.SPEED, "speed_international_system", "m/y", 1.0 / 60 / 60 / 24 / 365, false),
    DEKAMETER_PER_YEAR(UnitType.SPEED, "speed_international_system", "dm/y", 1E1 / 60 / 60 / 24 / 365, false),
    KILOMETER_PER_YEAR(UnitType.SPEED, "speed_international_system", "km/y", 1E3 / 60 / 60 / 24 / 365, false),
    CENTIMETER_PER_YEAR(UnitType.SPEED, "speed_international_system", "cm/y", 1E-2 / 60 / 60 / 24 / 365, false),
    MILLIMETER_PER_YEAR(UnitType.SPEED, "speed_international_system", "mm/y", 1E-3 / 60 / 60 / 24 / 365, false),

    BAR(UnitType.PRESSURE, "pressure_metric", null, 1, true),
    KILOPASCAL(UnitType.PRESSURE, "pressure_metric", "kPa", 1E-2, false),
    HECTOPASCAL(UnitType.PRESSURE, "pressure_metric", "hPa", 1E-3, false),
    MEGAPASCAL(UnitType.PRESSURE, "pressure_metric", "MPa", 1E1, false),
    MILLIBAR(UnitType.PRESSURE, "pressure_metric", null, 1E-3, false),
    PASCAL(UnitType.PRESSURE, "pressure_metric", "Pa", 1E-5, false),
    GRAM_PER_SQUARE_CENTIMETER(UnitType.PRESSURE, "pressure_metric", "gf/cm²", 0.000980665012, false),
    TONNE_PER_SQUARE_CENTIMETER(UnitType.PRESSURE, "pressure_metric", "tf/cm²", 980.665012, false),
    KILOGRAM_PER_SQUARE_METER(UnitType.PRESSURE, "pressure_metric", "kgf/m²", 0.0000980665012, false),
    TONNE_PER_SQUARE_METER(UnitType.PRESSURE, "pressure_metric", "tf/m²", 0.0980665012, false),
    NEWTON_PER_SQUARE_METER(UnitType.PRESSURE, "pressure_metric", "N/m²", 1E-5, false),
    KILONEWTON_PER_SQUARE_METER(UnitType.PRESSURE, "pressure_metric", "kN/m²", 1E-2, false),
    MEGANEWTON_PER_SQUARE_METER(UnitType.PRESSURE, "pressure_metric", "MN/m²", 1E1, false),
    NEWTON_PER_SQUARE_CENTIMETER(UnitType.PRESSURE, "pressure_metric", "N/cm²", 1E-1, false),
    NEWTON_PER_SQUARE_MILLIMETER(UnitType.PRESSURE, "pressure_metric", "N/mm²", 1E1, false),

    OUNCE_PER_SQUARE_INCH(UnitType.PRESSURE, "pressure_imperial", "oz/in²", 1, true),
    OUNCE_PER_SQUARE_FOOT(UnitType.PRESSURE, "pressure_imperial", "oz/ft²", 0.00694444444, false),
    POUND_PER_SQUARE_INCH(UnitType.PRESSURE, "pressure_imperial", "psi", 16, false),
    POUND_PER_SQUARE_FOOT(UnitType.PRESSURE, "pressure_imperial", "lb/ft²", 0.111111111, false),
    THOUSAND_POUNDS_PER_SQUARE_INCH(UnitType.PRESSURE, "pressure_imperial", "ksi", 16000, false),

    METER_OF_WATER(UnitType.PRESSURE, "pressure_water", "m.w.с", 1, true),
    CENTIMETER_OF_WATER(UnitType.PRESSURE, "pressure_water", "cm.w.с", 1E-2, false),
    MILLIMETER_OF_WATER(UnitType.PRESSURE, "pressure_water", "mm.w.с", 1E-3, false),
    FOOT_OF_WATER(UnitType.PRESSURE, "pressure_water", "ft.w.с", 0.3048, false),
    INCH_OF_WATER(UnitType.PRESSURE, "pressure_water", "in.w.с", 0.0253999998, false),

    CENTIMETER_OF_MERCURY(UnitType.PRESSURE, "pressure_mercury", "cm.m.c", 1, true),
    METER_OF_MERCURY(UnitType.PRESSURE, "pressure_mercury", "m.m.c", 100, false),
    MILLIMETER_OF_MERCURY(UnitType.PRESSURE, "pressure_mercury", "mm.m.c", 1E-1, false),
    INCH_OF_MERCURY(UnitType.PRESSURE, "pressure_mercury", "in.m.c", 2.54, false),

    PHYSICAL_ATMOSPHERE(UnitType.PRESSURE, "pressure_atmosphere", "atm", 1, true),
    TECHNICAL_ATMOSPHERE(UnitType.PRESSURE, "pressure_atmosphere", null, 0.967841061, false),

    SQUARE_METER_PER_SECOND(UnitType.VISCOSITY, "viscosity", "m²/s", 1, true),
    SQUARE_MILLIMETER_PER_SECOND(UnitType.VISCOSITY, "viscosity", "mm²/s", 1, false),


    ;
    String fullName;
    UnitType type;
    String subtype;
    String shortName;
    BigDecimal coefficient;
    Boolean isPrimary;
    BigDecimal term;
    BigDecimal coefficientFromPrimary;
    BigDecimal termFromPrimary;

    Unit(UnitType type,
         String subtype,
         String shortName,
         BigDecimal coefficient,
         Boolean isPrimary) {
        this.fullName = name();
        this.type = type;
        this.subtype = subtype;
        this.shortName = shortName;
        this.coefficient = coefficient;
        this.isPrimary = isPrimary;
        this.term = null;
        this.coefficientFromPrimary = null;
        this.termFromPrimary = null;
    }

    Unit(UnitType type,
         String subtype,
         String shortName,
         double coefficient,
         Boolean isPrimary) {
        this(type,
                subtype,
                shortName,
                BigDecimal.valueOf(coefficient),
                isPrimary);
    }

    Unit(UnitType type,
         String subtype,
         String shortName,
         BigDecimal coefficient,
         BigDecimal term,
         BigDecimal coefficientFromPrimary,
         BigDecimal termFromPrimary,
         Boolean isPrimary
    ) {
        this.fullName = name();
        this.type = type;
        this.subtype = subtype;
        this.shortName = shortName;
        this.coefficient = coefficient;
        this.term = term;
        this.coefficientFromPrimary = coefficientFromPrimary;
        this.termFromPrimary = termFromPrimary;
        this.isPrimary = isPrimary;
    }

    Unit(UnitType type,
         String subtype,
         String shortName,
         double coefficient,
         double term,
         double coefficientFromPrimary,
         double termFromPrimary,
         Boolean isPrimary
    ) {
        this(type,
                subtype,
                shortName,
                BigDecimal.valueOf(coefficient),
                BigDecimal.valueOf(term),
                BigDecimal.valueOf(coefficientFromPrimary),
                BigDecimal.valueOf(termFromPrimary),
                isPrimary);
    }

    @JsonCreator
    public static Unit valueOfFullName(@JsonProperty("fullName") String fullName) {
        return Arrays.stream(values())
                .filter(u -> u.name().equals(fullName.toUpperCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit \"" + fullName + "\". " +
                        "Follow \"/converters/units\" to check available units"));
    }

    public static Unit valueOfSubtypeAndIsPrimaryIsTrue(String subType) {
        return Arrays.stream(values())
                .filter(u -> u.getIsPrimary() && u.getSubtype().equals(subType.toLowerCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit with subtype \"" + subType + "\". " +
                        "Follow \"/converters/units\" to check available units and subtypes"));
    }

    public static List<Unit> valueOfType(UnitType unitType) {
        return Arrays.stream(values())
                .filter(unit -> unit.getType() == unitType)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "{unitType = " + type
                + ", fullName = " + fullName
                + ", shortName = " + shortName + "}";
    }
}
