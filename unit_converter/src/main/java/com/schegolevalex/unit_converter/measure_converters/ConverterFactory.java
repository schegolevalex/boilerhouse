package com.schegolevalex.unit_converter.measure_converters;

import com.schegolevalex.library.entities.units.UnitType;
import com.schegolevalex.unit_converter.exceptions.IllegalMeasureException;
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
                .filter(c -> c.getType() == unitType)
                .findFirst()
                .orElse(converters
                        .stream()
                        .filter(c -> c.getType() == UnitType.DEFAULT)
                        .findFirst()
                        .orElseThrow(() -> new IllegalMeasureException("No converter for this this unit type.")));
    }

}
