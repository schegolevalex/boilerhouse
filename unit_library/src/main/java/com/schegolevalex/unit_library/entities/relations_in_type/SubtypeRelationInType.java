package com.schegolevalex.unit_library.entities.relations_in_type;

import com.schegolevalex.unit_library.entities.units.UnitType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

    SubtypeRelationInType(UnitType unitType, String subtypeFrom, String subtypeTo, BigDecimal subtypeCoefficient) {
        this.unitType = unitType;
        this.subtypeFrom = subtypeFrom;
        this.subtypeTo = subtypeTo;
        this.subtypeCoefficient = subtypeCoefficient;
    }

    SubtypeRelationInType(UnitType unitType, String subtypeFrom, String subtypeTo, double subtypeCoefficient) {
        this(unitType, subtypeFrom, subtypeTo, BigDecimal.valueOf(subtypeCoefficient));
    }

}
