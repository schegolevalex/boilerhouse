package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MeasureConverter {
    private final UnitRepository unitRepository;

    public MeasureConverter(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Measure convert(Measure measure, Unit unitTo) {

        if (measure.getUnit().getType() == unitTo.getType()) {
            BigDecimal resultValue = measure.getValue()
                    .multiply(measure.getUnit().getCoefficient())
                    .divide(unitTo.getCoefficient());

            measure.setValue(resultValue);
            measure.setUnit(unitTo);
            return measure;
        } else throw new IllegalArgumentException();
    }

    public Measure convert(BigDecimal value, Unit unitFrom, Unit unitTo) {
        Measure inputMeasure = new Measure(value, unitFrom);
        return convert(inputMeasure, unitTo);
    }

    public Measure convertToPrimary (BigDecimal value, Unit unit) {
        Measure primary = new Measure(value, unit);
        return convert(primary, unitRepository.getByTypeAndIsPrimaryIsTrue(unit.getType()));
    }
}
