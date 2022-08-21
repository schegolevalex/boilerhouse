package unit_converter.measure_converters;

import unit_converter.entities.units.UnitType;
import unit_converter.exceptions.IllegalMeasureException;
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
        MeasureConverter converter = converters.stream().filter(c -> c.getType() == unitType).findFirst().orElse(null);
        if (converter == null) {
            throw new IllegalMeasureException("No converter for unit type \"" + unitType + "\".");
        } else return converter;
    }

}
