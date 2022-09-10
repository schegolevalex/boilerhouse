package com.schegolevalex.unit_converter.entities.measures;

import com.schegolevalex.unit_converter.entities.measures.constraints.MeasureConstraint;
import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MeasureFactory {
    private final List<MeasureConstraint> constraintsList;

    public MeasureFactory(List<MeasureConstraint> constraintsList) {
        this.constraintsList = constraintsList;
    }

    public Measure createMeasure (BigDecimal value, Unit unit) {
        constraintsList.stream().filter(c -> c.getType() == unit.getType()).forEach(c -> c.check(value, unit));
        return new Measure(value, unit);
    }
}
