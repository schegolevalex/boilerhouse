package com.schegolevalex.unit_converter.measure_converters;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import com.schegolevalex.unit_library.services.SubtypeRelationInTypeService;
import com.schegolevalex.unit_library.services.UnitService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class MeasureConverter {
    @Getter
    UnitType converterType = UnitType.DEFAULT;
    final UnitService unitService;
    final SubtypeRelationInTypeService subtypeRelationInTypeService;
    final MeasureFactory measureFactory;

    @Autowired
    public MeasureConverter(UnitService unitService,
                            SubtypeRelationInTypeService subtypeRelationInTypeService,
                            MeasureFactory measureFactory) {
        this.unitService = unitService;
        this.subtypeRelationInTypeService = subtypeRelationInTypeService;
        this.measureFactory = measureFactory;
    }

    public Measure convert(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        //проверка на соответствие типов
        isTheSameType(unitFrom, unitTo);

        //получили коэффициент из отношения подтипов
        BigDecimal subtypeCoefficient = getSubtypeCoefficient(unitFrom, unitTo);

        //сконвертировали исходное Measure в primary исходного типа
        Measure primaryMeasureFrom = convertToPrimary(valueFrom, unitFrom);

        //и умножили его на коэффициент из отношения подтипов, полученный выше.
        //Тем самым мы перевели исходное значение valueFrom в primary другого типа.
        Measure primaryMeasureTo = measureFactory.createMeasure(primaryMeasureFrom.getValue().multiply(subtypeCoefficient),
                unitService.findBySubtypeAndIsPrimaryIsTrue(unitTo.getSubtype()));

        //Получили primary Unit другого типа и сконвертировали valueTo с прошлого шага в целевой Unit.
        return convertFromPrimary(primaryMeasureTo, unitTo);
    }

    public Measure convert(Measure measureFrom, Unit unitTo) {
        BigDecimal valueFrom = measureFrom.getValue();
        Unit unitFrom = measureFrom.getUnit();
        return convert(valueFrom, unitFrom, unitTo);
    }

    Measure convertToPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        BigDecimal valueTo = valueFrom
                .multiply(unitFrom.getCoefficient())
                .divide(unitTo.getCoefficient(), 10, RoundingMode.HALF_UP)
                .stripTrailingZeros();
        return measureFactory.createMeasure(valueTo, unitTo);
    }

    public Measure convertToPrimary(BigDecimal valueFrom, Unit unitFrom) {
        return convertToPrimary(valueFrom,
                unitFrom,
                unitService.findBySubtypeAndIsPrimaryIsTrue(unitFrom.getSubtype()));
    }

    Measure convertToPrimary(Measure measureFrom, Unit unitTo) {
        BigDecimal valueFrom = measureFrom.getValue();
        Unit unitFrom = measureFrom.getUnit();
        return convertToPrimary(valueFrom, unitFrom, unitTo);
    }

    Measure convertFromPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        return convertToPrimary(valueFrom, unitFrom, unitTo);
    }

    Measure convertFromPrimary(Measure measureFrom, Unit unitTo) {
        BigDecimal valueFrom = measureFrom.getValue();
        Unit unitFrom = measureFrom.getUnit();
        return convertFromPrimary(valueFrom, unitFrom, unitTo);
    }

    List<Measure> convertAll(Measure measureFrom) {
        List<Unit> unitsOfType = unitService.findByType(measureFrom.getUnit().getUnitType());
        List<Measure> measureList = new ArrayList<>();
        for (Unit unit : unitsOfType) {
            measureList.add(convert(measureFrom, unit));
        }
        return measureList;
    }

    void isTheSameType(Unit unitFrom, Unit unitTo) {
        if (unitFrom.getUnitType() != unitTo.getUnitType())
            throw new IllegalUnitException("Conversion is not possible. Units has different types.");
    }

    void isTheSameType(Measure measureFrom, Unit unitTo) {
        Unit unitFrom = measureFrom.getUnit();
        isTheSameType(unitFrom, unitTo);
    }

    BigDecimal getSubtypeCoefficient(Unit unitFrom, Unit unitTo) {
        if (unitFrom.getSubtype() != null && unitTo.getSubtype() != null && !unitFrom.getSubtype().equals(unitTo.getSubtype()))
            return subtypeRelationInTypeService.getByRelationBySubtypes(unitFrom, unitTo).getSubtypeCoefficient();
        else
            return BigDecimal.valueOf(1.0);
    }
}