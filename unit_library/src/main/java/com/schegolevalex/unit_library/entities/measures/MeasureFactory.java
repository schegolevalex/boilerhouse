package com.schegolevalex.unit_library.entities.measures;

import com.schegolevalex.unit_library.entities.measures.constraints.MeasureConstraint;
import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MeasureFactory {
    private final List<MeasureConstraint> constraintsList;

    @Autowired
    public MeasureFactory(List<MeasureConstraint> constraintsList) {
        this.constraintsList = constraintsList;
    }

    public Measure createMeasure (BigDecimal value, Unit unit) {
        constraintsList.stream().filter(c -> c.getUnitType() == unit.getUnitType()).forEach(c -> c.check(value, unit));
        return new Measure(value, unit);
    }

    public Measure createMeasure (double value, Unit unit) {
        constraintsList.stream().filter(c -> c.getUnitType() == unit.getUnitType()).forEach(c -> c.check(BigDecimal.valueOf(value), unit));
        return new Measure(BigDecimal.valueOf(value), unit);
    }
}
