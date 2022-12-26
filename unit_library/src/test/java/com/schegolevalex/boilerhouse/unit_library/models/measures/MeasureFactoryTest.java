package com.schegolevalex.boilerhouse.unit_library.models.measures;

import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalMeasureException;
import com.schegolevalex.boilerhouse.unit_library.models.measures.constraints.TemperatureConstraint;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeasureFactoryTest {

    @Mock
    TemperatureConstraint constraint1; // будет выбрасывать исключение

    @Mock
    TemperatureConstraint constraint2; // НЕ БУДЕТ выбрасывать исключение


    // 1. подменяем measureConstraint1 и measureConstraint2, делаем, чтобы measureConstraint2 выбрасывал исключение
    // и проверяем выброс исключения на второй вызов метода check()
    // 2. подменяем measureConstraint и проверяем создание ЛЮБОГО Measure (c любыми параметрами)

    @Test
    void createMeasure_throwsIllegalMeasureException_whenCreateTemperatureMeasure() {
        doReturn(UnitType.TEMPERATURE).when(constraint1).getUnitType();
        doReturn(UnitType.TEMPERATURE).when(constraint2).getUnitType();
        doThrow(IllegalMeasureException.class)
                .when(constraint2)
                .check(any(BigDecimal.class), any(Unit.class));

        MeasureFactory measureFactory = new MeasureFactory(Arrays.asList(constraint1, constraint2));

        assertThrows(IllegalMeasureException.class, () -> measureFactory.createMeasure(5.0, Unit.KELVIN));
        verify(constraint1, times(1)).getUnitType();
        verify(constraint2, times(1)).getUnitType();
        verify(constraint1).check(any(BigDecimal.class), any(Unit.class));
        verify(constraint2).check(any(BigDecimal.class), any(Unit.class));
    }

    @Test
    void createMeasure_createMeasure_whenInputAnyParameters() {
        doReturn(UnitType.TEMPERATURE).when(constraint1).getUnitType();

        doThrow(IllegalMeasureException.class)
                .when(constraint1)
                .check(any(BigDecimal.class), any(Unit.class));

        MeasureFactory measureFactory = new MeasureFactory(Collections.singletonList(constraint1));
        assertThrows(IllegalMeasureException.class, () -> measureFactory.createMeasure(5.0, Unit.KELVIN));
    }
}