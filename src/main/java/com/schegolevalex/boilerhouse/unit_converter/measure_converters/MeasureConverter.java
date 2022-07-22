package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;

import java.math.BigDecimal;

public abstract class MeasureConverter {
    private UnitType converterType;

    public abstract Measure convert(Measure measure, Unit unitTo);

    public abstract Measure convert(BigDecimal value, Unit unitFrom, Unit unitTo);

    public abstract Measure convertToPrimary(Measure measure);

    public abstract Measure convertToPrimary(BigDecimal value, Unit unit);

    public UnitType getType() {
        return converterType;
    };
}
