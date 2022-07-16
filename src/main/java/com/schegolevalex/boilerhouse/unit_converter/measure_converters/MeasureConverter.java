package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;

import java.math.BigDecimal;

public interface MeasureConverter {

    Measure convert(Measure measure, Unit unitTo);

    Measure convertToPrimary(Measure measure);

    UnitType getType();
}
