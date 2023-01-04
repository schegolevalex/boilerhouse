package com.schegolevalex.boilerhouse.pid.services.unit_converter;

import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalMeasureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConverterFactory {
    private final List<MeasureConverter> converters;

    @Autowired
    public ConverterFactory(List<MeasureConverter> converters) {
        this.converters = converters;
    }

    public MeasureConverter getConverter(UnitType unitType) {
        return converters
                .stream()
                .filter(c -> c.getConverterType() == unitType)
                .findFirst()
                .orElse(converters
                        .stream()
                        .filter(c -> c.getConverterType() == UnitType.DEFAULT)
                        .findFirst()
                        .orElseThrow(() -> new IllegalMeasureException("No converter for this this unit type.")));
    }

}
