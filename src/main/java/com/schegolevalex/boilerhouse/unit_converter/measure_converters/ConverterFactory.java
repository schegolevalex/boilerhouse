package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
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
        return converters.stream().filter(c -> c.getType() == unitType).findFirst().orElse(null);
    }

}
