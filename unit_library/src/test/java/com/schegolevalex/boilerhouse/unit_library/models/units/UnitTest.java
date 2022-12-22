package com.schegolevalex.boilerhouse.unit_library.models.units;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void whenInputName_ThenReturnedUnit() {
        assertEquals(Unit.valueOfFullName("METER"), Unit.METER);
    }

    @Test
    void valueOfSubtypeAndIsPrimaryIsTrue() {
        assertEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("distance_meTric"), Unit.METER);
        assertEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("power_commoN_units"), Unit.GIGACALORIES_PER_HOUR);
        assertNotEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("power_commoN_units"), Unit.GIGACALORIES_PER_SECOND);
    }

    @Test
    void valueOfType() {
        List<Unit> unitsListFromMethod = Unit.valueOfType(UnitType.TEMPERATURE);
        List<Unit> expectedUnitsList = List.of(Unit.DEGREE_CELSIUS, Unit.DEGREE_FAHRENHEIT, Unit.KELVIN);
        assertTrue(unitsListFromMethod.size() == expectedUnitsList.size() && unitsListFromMethod.containsAll(expectedUnitsList));
    }

    @Test
    void getFullName() {
        assertEquals(Unit.METER.getFullName(), "METER");
    }

    @Test
    void getUnitType() {
        assertEquals(Unit.METER.getUnitType(), UnitType.DISTANCE);
    }

    @Test
    void getSubtype() {
        assertEquals(Unit.METER.getSubtype(), "distance_metric");
    }

    @Test
    void getShortName() {
        assertEquals(Unit.METER.getShortName(), "m");
    }

    @Test
    void getCoefficient() {
        assertEquals(Unit.METER.getCoefficient(), BigDecimal.valueOf(1.0));
    }

    @Test
    void getIsPrimary() {
        assertTrue(Unit.METER.getIsPrimary());
        assertFalse(Unit.DEGREE_FAHRENHEIT.getIsPrimary());
    }

    @Test
    void getTerm() {
        assertNull(Unit.METER.getTerm());
        assertEquals(Unit.DEGREE_CELSIUS.getTerm(), BigDecimal.valueOf(0.0));
    }

    @Test
    void getCoefficientFromPrimary() {
        assertNull(Unit.METER.getCoefficientFromPrimary());
        assertEquals(Unit.DEGREE_CELSIUS.getCoefficientFromPrimary(), BigDecimal.valueOf(1.0));
    }

    @Test
    void getTermFromPrimary() {
        assertNull(Unit.METER.getTermFromPrimary());
        assertEquals(Unit.DEGREE_CELSIUS.getTermFromPrimary(), BigDecimal.valueOf(0.0));
    }
}