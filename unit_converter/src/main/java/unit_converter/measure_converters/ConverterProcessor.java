package unit_converter.measure_converters;

import unit_converter.entities.measures.Measure;
import unit_converter.entities.measures.MeasureFactory;
import unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConverterProcessor {
    private final MeasureFactory measureFactory;
    private final ConverterFactory factory;

    @Autowired
    public ConverterProcessor(MeasureFactory measureFactory, ConverterFactory factory) {
        this.measureFactory = measureFactory;
        this.factory = factory;
    }

    public Measure getConvertedResult(BigDecimal value, Unit unitFrom, Unit unitTo) throws Throwable {
        Measure measure = measureFactory.createMeasure(value, unitFrom);
        MeasureConverter converter = factory.getConverter(unitFrom.getType());
        return converter.convert(measure, unitTo);
    }
}
