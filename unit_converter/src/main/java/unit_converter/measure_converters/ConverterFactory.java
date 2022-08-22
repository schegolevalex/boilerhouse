package unit_converter.measure_converters;

import unit_converter.entities.units.UnitType;
import unit_converter.exceptions.IllegalMeasureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class ConverterFactory {
    private final List<MeasureConverter> converters;

    @Autowired
    public ConverterFactory(List<MeasureConverter> converters) {
        this.converters = converters;
    }

    public MeasureConverter getConverter(UnitType unitType) throws Throwable {
        return converters
                .stream()
                .filter(c -> c.getType() == unitType)
                .findFirst()
                .orElse(converters
                        .stream()
                        .filter(c -> c.getType() == UnitType.DEFAULT)
                        .findFirst()
                        .orElseThrow((Supplier<Throwable>) () -> new IllegalMeasureException("No converter for this this unit type.")));
    }

}
