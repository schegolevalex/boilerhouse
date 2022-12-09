package com.schegolevalex.boilerhouse.pid.services.unit_converter;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ConverterService {
    private final MeasureFactory measureFactory;
    private final ConverterFactory factory;

    @Autowired
    public ConverterService(MeasureFactory measureFactory, ConverterFactory factory) {
        this.measureFactory = measureFactory;
        this.factory = factory;
    }

    public Measure getConvertedResult(Measure measureFrom, Unit unitTo) {
        return factory.getConverter(unitTo.getUnitType()).convert(measureFrom, unitTo);
    }

    public Measure getConvertedResult(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        Measure measure = measureFactory.createMeasure(valueFrom, unitFrom);
        return getConvertedResult(measure, unitTo);
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
