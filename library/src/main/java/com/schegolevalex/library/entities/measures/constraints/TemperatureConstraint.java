package com.schegolevalex.library.entities.measures.constraints;

import com.schegolevalex.unit_converter.entities.measures.Measure;
import com.schegolevalex.unit_converter.entities.units.Unit;
import com.schegolevalex.unit_converter.entities.units.UnitType;
import com.schegolevalex.unit_converter.exceptions.IllegalMeasureException;
import com.schegolevalex.unit_converter.measure_converters.TemperatureConverter;
import com.schegolevalex.unit_converter.repositories.UnitRepository;
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
    public void check(BigDecimal value, Unit clientUnit) {
        Measure primaryMeasure = converter.convert(value,
                clientUnit,
                unitRepository.getBySubtypeAndIsPrimaryIsTrue(clientUnit.getSubtype()));

        if (primaryMeasure.getValue().compareTo(BigDecimal.valueOf(-273.15)) < 0) {
            Measure constraintMeasure = new Measure(BigDecimal.valueOf(-273.15), unitRepository.getByFullName("DEGREE_CELSIUS"));
            Measure convertedConstraintMeasure = converter.convert(constraintMeasure, clientUnit);
            throw new IllegalMeasureException("The value must be greater than "
                    + convertedConstraintMeasure.getValue() + ""
                    + convertedConstraintMeasure.getUnit().getShortName());
        }
    }
}
