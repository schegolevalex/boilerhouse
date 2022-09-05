package com.schegolevalex.unit_converter.entities.units;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public abstract class Distance extends Unit {
    public Distance(UnitType unitType, String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(unitType, fullName, shortName, coefficient, isPrimary);
    }
}
