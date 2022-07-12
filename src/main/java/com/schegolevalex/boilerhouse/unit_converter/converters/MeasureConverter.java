package com.schegolevalex.boilerhouse.unit_converter.converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measure.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;

public class MeasureConverter {
    static Measure convert(Measure measure, Unit unit) {

//        if (unit.getMeasureUnit() instanceof measureUnitClass) {
            measure.setValue(measure.getValue() * measure.getUnit().getCoefficient() / unit.getCoefficient());
            measure.setUnit(unit);
            return measure;
//        } else throw new IllegalUnitException();
    }
}
