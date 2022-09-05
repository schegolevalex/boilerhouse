package com.schegolevalex.library.entities.measures.constraints;

import com.schegolevalex.unit_converter.entities.units.Unit;
import com.schegolevalex.unit_converter.entities.units.UnitType;
import com.schegolevalex.unit_converter.measure_converters.MeasureConverter;
import com.schegolevalex.unit_converter.repositories.UnitRepository;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    final MeasureConverter converter;
    final UnitRepository unitRepository;
    UnitType type;

    MeasureConstraint(MeasureConverter converter, UnitRepository unitRepository) {
        this.converter = converter;
        this.unitRepository = unitRepository;
    }

    public abstract void check(BigDecimal value, Unit clientUnit);
}
