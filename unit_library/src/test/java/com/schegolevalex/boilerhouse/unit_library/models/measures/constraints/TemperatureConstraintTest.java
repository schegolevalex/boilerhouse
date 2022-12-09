package com.schegolevalex.boilerhouse.unit_library.models.measures.constraints;

import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalMeasureException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TemperatureConstraintTest {

    @Test
    public void givenValueAndTemperatureUnit_whenCheckConstraint_thenShouldThrowException() {
        TemperatureConstraint temperatureConstraint = new TemperatureConstraint();
        Assert.assertThrows(IllegalMeasureException.class, () -> temperatureConstraint.check(BigDecimal.valueOf(-274), Unit.DEGREE_CELSIUS));
        Assert.assertThrows(IllegalMeasureException.class, () -> temperatureConstraint.check(BigDecimal.valueOf(-1), Unit.KELVIN));
        Assert.assertThrows(IllegalMeasureException.class, () -> temperatureConstraint.check(BigDecimal.valueOf(-460), Unit.DEGREE_FAHRENHEIT));
    }

    @Test
    public void givenValueAndTemperatureUnit_whenCheckConstraint_thenShouldNotThrowException() {
        TemperatureConstraint temperatureConstraint = new TemperatureConstraint();
        temperatureConstraint.check(BigDecimal.valueOf(-273), Unit.DEGREE_CELSIUS);
        temperatureConstraint.check(BigDecimal.valueOf(0), Unit.KELVIN);
        temperatureConstraint.check(BigDecimal.valueOf(-459), Unit.DEGREE_FAHRENHEIT);
    }
}