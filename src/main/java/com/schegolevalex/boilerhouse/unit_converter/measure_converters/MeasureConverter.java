package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;

import java.math.BigDecimal;

public class MeasureConverter {
    public static Measure convert(Measure measure, Unit to) {

        if (measure.getUnit().getType() == to.getType()) {
            BigDecimal resultValue = measure.getValue()
                    .multiply(measure.getUnit().getCoefficient())
                    .divide(to.getCoefficient());

            measure.setValue(resultValue);
            measure.setUnit(to);
            return measure;
        } else throw new IllegalArgumentException();
    }

    public static Measure convertToPrimary (Measure measure) {
        Unit primary =

        BigDecimal resultValue = measure.getValue()
                .multiply(measure.getUnit().getCoefficient())
                .divide(primary.getCoefficient());

        measure.setValue(resultValue);
        measure.setUnit(primary);
        return measure;
    }
}
