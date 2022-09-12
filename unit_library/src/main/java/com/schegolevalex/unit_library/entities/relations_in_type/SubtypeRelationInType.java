package com.schegolevalex.unit_library.entities.relations_in_type;

import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.schegolevalex.unit_library.entities.units.UnitType.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SubtypeRelationInType {
    DISTANCE_METRIC_TO_IMPERIAL(DISTANCE, "distance_metric", "distance_imperial", 39.3700787),
    POWER_INTERNATIONAL_SYSTEM_TO_COMMON_UNITS(POWER, "power_international_system", "power_common_units", 0.85984523),
    FLOW_RATE_BY_MASS_METRIC_TO_IMPERIAL(FLOW_RATE_BY_MASS, "flow_rate_by_mass_metric", "flow_rate_by_mass_imperial", 2204.62262),
    MASS_METRIC_TO_IMPERIAL(MASS, "mass_metric", "mass_imperial", 2204.62262),

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
