package com.schegolevalex.boilerhouse.pid.services.unit_converter;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalUnitException;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import com.schegolevalex.boilerhouse.unit_library.services.SubtypeRelationInTypeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeasureConverter {
    @Getter
    UnitType converterType = UnitType.DEFAULT;
    final SubtypeRelationInTypeService subtypeRelationInTypeService;
    final MeasureFactory measureFactory;

    @Autowired
    public MeasureConverter(SubtypeRelationInTypeService subtypeRelationInTypeService,
                            MeasureFactory measureFactory) {
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
                Unit.valueOfSubtypeAndIsPrimaryIsTrue(unitTo.getSubtype()));

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
                Unit.valueOfSubtypeAndIsPrimaryIsTrue(unitFrom.getSubtype()));
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
        List<Unit> unitsOfType = Unit.valueOfType(measureFrom.getUnit().getType());
        List<Measure> measureList = new ArrayList<>();
        for (Unit unit : unitsOfType) {
            measureList.add(convert(measureFrom, unit));
        }
        return measureList;
    }

    void isTheSameType(Unit unitFrom, Unit unitTo) {
        if (unitFrom.getType() != unitTo.getType())
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
