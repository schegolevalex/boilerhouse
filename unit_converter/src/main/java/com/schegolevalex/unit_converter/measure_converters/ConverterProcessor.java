package com.schegolevalex.unit_converter.measure_converters;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ConverterProcessor {
    private final MeasureFactory measureFactory;
    private final ConverterFactory factory;

    @Autowired
    public ConverterProcessor(MeasureFactory measureFactory, ConverterFactory factory) {
        this.measureFactory = measureFactory;
        this.factory = factory;
    }

    public Measure getConvertedResult(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        Measure measure = measureFactory.createMeasure(valueFrom, unitFrom);
        MeasureConverter converter = factory.getConverter(unitFrom.getUnitType());
        return converter.convert(measure, unitTo);
    }

    public Measure getConvertedToPrimaryResult(BigDecimal valueFrom, Unit unitFrom) {
        MeasureConverter converter = factory.getConverter(unitFrom.getUnitType());
        return converter.convertToPrimary(valueFrom, unitFrom);
    }

    public List<Measure> getConvertedResultList(BigDecimal valueFrom, Unit unitFrom) {
        Measure measure = measureFactory.createMeasure(valueFrom, unitFrom);
        MeasureConverter converter = factory.getConverter(unitFrom.getUnitType());
        return converter.convertAll(measure);
    }
}
