package com.schegolevalex.unit_library.entities.measures.constraints;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.exceptions.IllegalMeasureException;
import com.schegolevalex.unit_library.repositories.UnitRepository;
import com.schegolevalex.unit_converter.measure_converters.TemperatureConverter;
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
                unitService.getBySubtypeAndIsPrimaryIsTrue(clientUnit.getSubtype()));

        if (primaryMeasure.getValue().compareTo(BigDecimal.valueOf(-273.15)) < 0) {
            Measure constraintMeasure = new Measure(BigDecimal.valueOf(-273.15), unitService.getByFullName("DEGREE_CELSIUS"));
            Measure convertedConstraintMeasure = converter.convert(constraintMeasure, clientUnit);
            throw new IllegalMeasureException("The value must be greater than "
                    + convertedConstraintMeasure.getValue() + ""
                    + convertedConstraintMeasure.getUnit().getShortName());
        }
    }
}
