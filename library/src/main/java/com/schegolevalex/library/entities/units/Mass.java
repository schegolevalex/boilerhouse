package com.schegolevalex.library.entities.units;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public abstract class Mass extends Unit {
    public Mass(UnitType unitType, String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(unitType, fullName, shortName, coefficient, isPrimary);
    }
}
