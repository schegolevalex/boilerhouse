package com.schegolevalex.boilerhouse.unit_library.models.units;

import java.util.Arrays;
import java.util.Locale;

public enum UnitType {
    DEFAULT,
    DISTANCE,
    MASS,
    AREA,
    VOLUME,
    TEMPERATURE,
    PRESSURE,
    POWER,
    FLOW_RATE_BY_MASS,
    FLOW_RATE_BY_VOLUME,
    SPEED,
    VISCOSITY,



    ;
    public static UnitType valueOfFullName(String fullName) {
        return Arrays.stream(values())
                .filter(unitType -> unitType.name().equals(fullName.toUpperCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such unit type \"" + fullName + "\". " /*+
                        "Follow \"/converters/units\" to check available units"*/));
    }
}
