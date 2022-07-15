package com.schegolevalex.boilerhouse.unit_converter.entities.checkers;

import com.schegolevalex.boilerhouse.unit_converter.converters.MeasureConverter;
import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;

import java.math.BigDecimal;

public class TemperatureChecker implements MeasureChecker {
    @Override
    public boolean check(Measure measure) {
        Measure measureWithPrimaryValueAndUnit = MeasureConverter.convertToPrimary(measure);
        if (measureWithPrimaryValueAndUnit.getValue().compareTo(new BigDecimal(-273)) == -1
                || measureWithPrimaryValueAndUnit.getValue().compareTo(new BigDecimal(273)) == 1)
            return false;
        return true;
    }
}
