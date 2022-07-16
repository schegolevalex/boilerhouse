package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConverterProcessor {
    private final Measure measure;
    private final MeasureConverter converter;

    @Autowired
    public ConverterProcessor(Measure measure, MeasureConverter converter) {
        this.measure = measure;
        this.converter = converter;
    }

    public Measure getConvertedResult(BigDecimal value, Unit unitFrom, Unit unitTo) {
        return converter.convert(value, unitFrom, unitTo);
    }
}
