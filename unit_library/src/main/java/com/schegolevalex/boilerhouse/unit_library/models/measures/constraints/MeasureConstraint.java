package com.schegolevalex.boilerhouse.unit_library.models.measures.constraints;

import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import lombok.Getter;

import java.math.BigDecimal;

public abstract class MeasureConstraint {
    @Getter
    UnitType unitType;

    public abstract void check(BigDecimal value, Unit unit);
}
