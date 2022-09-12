package com.schegolevalex.unit_library.entities.measures.constraints;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.exceptions.IllegalMeasureException;
import com.schegolevalex.unit_library.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TemperatureConstraint extends MeasureConstraint {

    @Autowired
    public TemperatureConstraint(UnitService unitRepository) {
        super(unitRepository);
        this.unitType = UnitType.TEMPERATURE;
    }

    @Override
    public void check(BigDecimal value, Unit unit) {
        switch (unit.getFullName()) {
            case "DEGREE_CELSIUS":
                if (value.compareTo(BigDecimal.valueOf(-273.15)) < 0)
                    throw new IllegalMeasureException("The value must be greater than -273.15"
                            + unit.getShortName());
                break;
            case "KELVIN":
                if (value.compareTo(BigDecimal.valueOf(0)) < 0)
                    throw new IllegalMeasureException("The value must be greater than 0"
                            + unit.getShortName());
                break;
            case "DEGREE_FAHRENHEIT":
                if (value.compareTo(BigDecimal.valueOf(-459.67)) < 0)
                    throw new IllegalMeasureException("The value must be greater than -459.67"
                            + unit.getShortName());
                break;
        }
    }
}
