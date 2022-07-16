package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConverterProcessor {
    private final Measure measure;
    private final ConverterFactory factory;

    @Autowired
    public ConverterProcessor(Measure measure, ConverterFactory factory) {
        this.measure = measure;
        this.factory = factory;
    }

    public Measure getConvertedResult(BigDecimal value, Unit unitFrom, Unit unitTo) {
        MeasureConverter converter = factory.getConverter(unitFrom.getType());
        return converter.convert(value, unitFrom, unitTo);
    }
}
