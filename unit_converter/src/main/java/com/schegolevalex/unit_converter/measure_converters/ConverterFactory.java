package com.schegolevalex.unit_converter.measure_converters;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.exceptions.IllegalMeasureException;
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

    public MeasureConverter getConverter(Unit unitType) {
        return converters
                .stream()
                .filter(c -> c.getType() == unitType)
                .findFirst()
                .orElse(converters
                        .stream()
                        .filter(c -> c.getType() == Unit.DEFAULT)
                        .findFirst()
                        .orElseThrow(() -> new IllegalMeasureException("No converter for this this unit type.")));
    }

}
