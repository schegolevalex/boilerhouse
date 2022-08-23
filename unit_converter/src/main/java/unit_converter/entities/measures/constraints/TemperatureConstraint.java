package unit_converter.entities.measures.constraints;

import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.Unit;
import unit_converter.entities.units.UnitType;
import unit_converter.exceptions.IllegalMeasureException;
import unit_converter.measure_converters.TemperatureConverter;
import unit_converter.repositories.UnitRepository;
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
        Measure primaryMeasure = converter.convert(value,
                unit,
                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unit.getSubtype()));

        if (primaryMeasure.getValue().compareTo(BigDecimal.valueOf(-273.15)) < 0) {
            Measure constraintMeasure = new Measure(BigDecimal.valueOf(-273.15), unitRepository.getByFullName("DEGREE_CELSIUS"));
            Measure convertedConstraintMeasure = converter.convert(constraintMeasure, unit);
            throw new IllegalMeasureException("The value must be greater than "
                    + convertedConstraintMeasure.getValue() + ""
                    + convertedConstraintMeasure.getUnit().getShortName());
        }
    }
}