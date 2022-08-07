package com.schegolevalex.boilerhouse.unit_converter.entities.checkers;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.exceptions.IllegalMeasureException;
import com.schegolevalex.boilerhouse.unit_converter.measure_converters.TemperatureConverter;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TemperatureConstraint extends MeasureConstraint {

    @Autowired
    public TemperatureConstraint(TemperatureConverter converter, UnitRepository unitRepository) {
        super(converter, unitRepository);
        this.type = UnitType.TEMPERATURE;
    }

    @Override
    public void check(BigDecimal value, Unit unit) {
        Measure measureWithPrimaryValueAndUnit = converter.convertUtil(value, unit);
        if (measureWithPrimaryValueAndUnit.getValue().compareTo(BigDecimal.valueOf(0)) < 0) {
            Measure constraintMeasure = new Measure(BigDecimal.valueOf(0), unitRepository.getByFullName("KELVIN"));
            Measure convertedConstraintMeasure = converter.convert(constraintMeasure, unit);
            throw new IllegalMeasureException("Значение должно быть больше "
                    + convertedConstraintMeasure
                    + convertedConstraintMeasure.getUnit().getShortName());
        }
    }
}
