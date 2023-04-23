package com.schegolevalex.boilerhouse.unit_library.models.units;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void valueOfFullName_returnUnit_whenInputName() {
        assertEquals(Unit.valueOfFullName("METER"), Unit.METER);
    }

    @Test
    void valueOfSubtypeAndIsPrimaryIsTrue_returnUnitBySubtypeAndPrimaryTrue_whenInputSubtype() {
        assertEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("distance_meTric"), Unit.METER);
        assertEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("power_commoN_units"), Unit.GIGACALORIES_PER_HOUR);
        assertNotEquals(Unit.valueOfSubtypeAndIsPrimaryIsTrue("power_commoN_units"), Unit.GIGACALORIES_PER_SECOND);
    }

    @Test
    void valueOfType_returnUnitListByUnitType_whenInputUnitType() {
        List<Unit> unitsListFromMethod = Unit.valueOfType(UnitType.TEMPERATURE);
        List<Unit> expectedUnitsList = List.of(Unit.DEGREE_CELSIUS, Unit.DEGREE_FAHRENHEIT, Unit.KELVIN);
        assertTrue(unitsListFromMethod.size() == expectedUnitsList.size() && unitsListFromMethod.containsAll(expectedUnitsList));
    }

    @Test
    void getFullName_returnFullName_whenCall() {
        assertEquals(Unit.METER.getFullName(), "METER");
    }

    @Test
    void getUnitType_returnUnitType_whenCall() {
        assertEquals(Unit.METER.getType(), UnitType.DISTANCE);
    }

    @Test
    void getSubtype_returnSubtype_whenCall() {
        assertEquals(Unit.METER.getSubtype(), "distance_metric");
    }

    @Test
    void getShortName_returnShortName_whenCall() {
        assertEquals(Unit.METER.getShortName(), "m");
    }

    @Test
    void getCoefficient_returnCoefficient_whenCall() {
        assertEquals(Unit.METER.getCoefficient(), BigDecimal.valueOf(1.0));
    }

    @Test
    void getIsPrimary_returnPrimary_whenCall() {
        assertTrue(Unit.METER.getIsPrimary());
        assertFalse(Unit.DEGREE_FAHRENHEIT.getIsPrimary());
    }

    @Test
    void getTerm_returnTerm_whenCall() {
        assertNull(Unit.METER.getTerm());
        assertEquals(Unit.DEGREE_CELSIUS.getTerm(), BigDecimal.valueOf(0.0));
    }

    @Test
    void getCoefficientFromPrimary_returnCoefficientFromPrimary_whenCall() {
        assertNull(Unit.METER.getCoefficientFromPrimary());
        assertEquals(Unit.DEGREE_CELSIUS.getCoefficientFromPrimary(), BigDecimal.valueOf(1.0));
    }

    @Test
    void getTermFromPrimary_returnTermFromPrimary_whenCall() {
        assertNull(Unit.METER.getTermFromPrimary());
        assertEquals(Unit.DEGREE_CELSIUS.getTermFromPrimary(), BigDecimal.valueOf(0.0));
    }
}