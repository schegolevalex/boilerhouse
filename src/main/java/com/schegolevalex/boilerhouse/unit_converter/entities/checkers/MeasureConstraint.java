package com.schegolevalex.boilerhouse.unit_converter.entities.checkers;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.measure_converters.DistanceConverter;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    protected final DistanceConverter converter;
    protected final UnitRepository unitRepository;

    protected UnitType type;

    @Autowired
    protected MeasureConstraint(DistanceConverter converter, UnitRepository unitRepository) {
        this.converter = converter;
        this.unitRepository = unitRepository;
    }

    public void check(BigDecimal value, Unit unit){
    }
}
