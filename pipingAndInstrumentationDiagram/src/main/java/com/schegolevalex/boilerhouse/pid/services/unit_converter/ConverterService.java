package com.schegolevalex.boilerhouse.pid.services.unit_converter;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ConverterService {
    private final MeasureFactory measureFactory;
    private final ConverterFactory converterFactory;

    @Autowired
    public ConverterService(MeasureFactory measureFactory, ConverterFactory converterFactory) {
        this.measureFactory = measureFactory;
        this.converterFactory = converterFactory;
    }

    public Measure getConvertedResult(Measure measureFrom, Unit unitTo) {
        return converterFactory.getConverter(unitTo.getType()).convert(measureFrom, unitTo);
    }

    public Measure getConvertedResult(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        Measure measure = measureFactory.createMeasure(valueFrom, unitFrom);
        return getConvertedResult(measure, unitTo);
    }

    public Measure getConvertedToPrimaryResult(BigDecimal valueFrom, Unit unitFrom) {
        MeasureConverter converter = converterFactory.getConverter(unitFrom.getType());
        return converter.convertToPrimary(valueFrom, unitFrom);
    }

    public List<Measure> getConvertedResultList(BigDecimal valueFrom, Unit unitFrom) {
        Measure measure = measureFactory.createMeasure(valueFrom, unitFrom);
        MeasureConverter converter = converterFactory.getConverter(unitFrom.getType());
        return converter.convertAll(measure);
    }
}
