package com.schegolevalex.unit_converter.entities.measures.constraints;

import com.schegolevalex.library.entities.units.Unit;
import com.schegolevalex.library.entities.units.UnitType;
import com.schegolevalex.unit_converter.repositories.UnitService;
import com.schegolevalex.unit_converter.measure_converters.MeasureConverter;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    final MeasureConverter converter;
    final UnitService unitService;
    UnitType type;

    MeasureConstraint(MeasureConverter converter, UnitService unitService) {
        this.converter = converter;
        this.unitService = unitService;
    }

    public abstract void check(BigDecimal value, Unit clientUnit);
}
