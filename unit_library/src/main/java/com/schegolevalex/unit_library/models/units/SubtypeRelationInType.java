package com.schegolevalex.unit_library.models.units;

import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.schegolevalex.unit_library.models.units.UnitType.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SubtypeRelationInType {
    DISTANCE_METRIC_TO_IMPERIAL(DISTANCE, "distance_metric", "distance_imperial", 39.3700787),
    POWER_INTERNATIONAL_SYSTEM_TO_COMMON_UNITS(POWER, "power_international_system", "power_common_units", 0.85984523),
    FLOW_RATE_BY_MASS_METRIC_TO_IMPERIAL(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "flow_rate_by_mass_imperial", 2204.62262),
    MASS_METRIC_TO_IMPERIAL(MASS, "mass_metric", "mass_imperial", 2204.62262),

    // TODO заполнить все отношения

    PRESSURE_METRIC_TO_IMPERIAL(PRESSURE, "pressure_metric", "pressure_imperial", 232.06038),
    PRESSURE_METRIC_TO_MERCURY(PRESSURE, "pressure_metric", "pressure_mercury", 75.006158),
    PRESSURE_METRIC_TO_WATER(PRESSURE, "pressure_metric", "pressure_water", 10.197162),
    PRESSURE_METRIC_TO_ATMOSPHERE(PRESSURE, "pressure_metric", "pressure_atmosphere", 0.98692327),

    PRESSURE_IMPERIAL_TO_MERCURY(PRESSURE, "pressure_imperial", "pressure_mercury", 0.32321828),
    PRESSURE_IMPERIAL_TO_WATER(PRESSURE, "pressure_imperial", "pressure_water", 0.043941849),
    PRESSURE_IMPERIAL_TO_ATMOSPHERE(PRESSURE, "pressure_imperial", "pressure_atmosphere", 0.0042528727),

    PRESSURE_MERCURY_TO_WATER(PRESSURE, "pressure_mercury", "pressure_water", 0.135951),
    PRESSURE_MERCURY_TO_ATMOSPHERE(PRESSURE, "pressure_mercury", "pressure_atmosphere", 0.013157897),

    PRESSURE_WATER_TO_ATMOSPHERE(PRESSURE, "pressure_water", "pressure_atmosphere", 0.096784111),

    ;
    @Getter
    UnitType unitType;
    @Getter
    String subtypeFrom;
    @Getter
    String subtypeTo;
    @Getter
    BigDecimal subtypeCoefficient;

    static List<SubtypeRelationInType> SUBTYPE_RELATION_IN_TYPE_LIST;

    static {
        SUBTYPE_RELATION_IN_TYPE_LIST = Arrays.stream(values()).toList();
    }

    SubtypeRelationInType(UnitType unitType, String subtypeFrom, String subtypeTo, BigDecimal subtypeCoefficient) {
        this.unitType = unitType;
        this.subtypeFrom = subtypeFrom;
        this.subtypeTo = subtypeTo;
        this.subtypeCoefficient = subtypeCoefficient;
    }

    SubtypeRelationInType(UnitType unitType, String subtypeFrom, String subtypeTo, double subtypeCoefficient) {
        this(unitType, subtypeFrom, subtypeTo, BigDecimal.valueOf(subtypeCoefficient));
    }

    public static SubtypeRelationInType valueOfSubtypes(String subtypeFrom, String subtypeTo) {
        return SUBTYPE_RELATION_IN_TYPE_LIST
                .stream()
                .filter(relation -> (relation.getSubtypeFrom().equals(subtypeFrom) && relation.getSubtypeTo().equals(subtypeTo)) ||
                        (relation.getSubtypeFrom().equals(subtypeTo) && relation.getSubtypeTo().equals(subtypeFrom)))
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("Conversion is not possible. Units has same types, but relation between subtypes is not define."));
    }
}
