package com.schegolevalex.boilerhouse.unit_converter;

import com.schegolevalex.boilerhouse.unit_converter.converters.MeasureConverter;
import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterProcessor {
    private final Measure measure;

    @Autowired
    public ConverterProcessor(Measure measure) {
        this.measure = measure;
    }

    public Measure getConvertedResult (Measure inputMeasure, Unit to) {
        return MeasureConverter.convert(inputMeasure, to);
    }
}
