package com.schegolevalex.unit_library.entities.measures.constraints;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.repositories.UnitRepository;
import com.schegolevalex.unit_converter.measure_converters.MeasureConverter;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    final MeasureConverter converter;
    final UnitRepository unitService;
    UnitType type;

    MeasureConstraint(MeasureConverter converter, UnitRepository unitService) {
        this.converter = converter;
        this.unitService = unitService;
    }

    public abstract void check(BigDecimal value, Unit clientUnit);
}
