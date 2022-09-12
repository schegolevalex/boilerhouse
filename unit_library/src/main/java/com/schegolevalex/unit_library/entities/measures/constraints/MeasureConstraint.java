package com.schegolevalex.unit_library.entities.measures.constraints;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import lombok.Getter;

import java.math.BigDecimal;

public abstract class MeasureConstraint {
    @Getter
    UnitType unitType;

    public abstract void check(BigDecimal value, Unit unit);
}
