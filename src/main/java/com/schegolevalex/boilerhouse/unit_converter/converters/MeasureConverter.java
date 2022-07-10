package com.schegolevalex.boilerhouse.unit_converter.converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.Measure;
import com.schegolevalex.boilerhouse.unit_converter.measure_units.Unit;

public interface Converter {
    static Measure convert(Measure measure, Unit unit) {

//        if (unit.getMeasureUnit() instanceof measureUnitClass) {
            measure.setValue(measure.getValue() / measure.getUnit().getCoefficient() / unit.getCoefficient());
            measure.setUnit(unit);
            return measure;
//        } else throw new IllegalUnitException();
    }
}
