package com.schegolevalex.unit_library.entities.measures.constraints;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.services.UnitService;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    final UnitService unitService;
    UnitType unitType;

    MeasureConstraint(UnitService unitService) {
        this.unitService = unitService;
    }

    public abstract void check(BigDecimal value, Unit unit);
}
