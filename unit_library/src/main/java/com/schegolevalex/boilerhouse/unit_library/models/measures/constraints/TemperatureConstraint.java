package com.schegolevalex.boilerhouse.unit_library.models.measures.constraints;

import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalMeasureException;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TemperatureConstraint extends MeasureConstraint {

    public TemperatureConstraint() {
        this.unitType = UnitType.TEMPERATURE;
    }

    @Override
    public void check(BigDecimal value, Unit unit) {
        switch (unit.getFullName()) {
            case "DEGREE_CELSIUS":
                if (value.compareTo(BigDecimal.valueOf(-273.15)) < 0)
                    throw new IllegalMeasureException("The value must be greater than or equal -273.15"
                            + unit.getShortName());
                break;
            case "KELVIN":
                if (value.compareTo(BigDecimal.valueOf(0)) < 0)
                    throw new IllegalMeasureException("The value must be greater than or equal 0"
                            + unit.getShortName());
                break;
            case "DEGREE_FAHRENHEIT":
                if (value.compareTo(BigDecimal.valueOf(-459.67)) < 0)
                    throw new IllegalMeasureException("The value must be greater than or equal -459.67"
                            + unit.getShortName());
                break;
        }
    }
}
