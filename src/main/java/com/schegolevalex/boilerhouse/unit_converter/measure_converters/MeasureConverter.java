package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.Relation;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.RelationInType;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.exceptions.IllegalMeasureException;
import com.schegolevalex.boilerhouse.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.boilerhouse.unit_converter.repositories.RelationInTypeRepository;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public abstract class MeasureConverter {
    protected UnitType converterType;
    protected UnitRepository unitRepository;
    protected RelationInTypeRepository relationInTypeRepository;

    public Measure convert(Measure measureFrom, Unit unitTo) {
        //проверка на соответствие типов
        isTheSameType(measureFrom, unitTo);

        //получили коэффициент из отношения подтипов
        BigDecimal subtypeCoefficient = BigDecimal.valueOf(1);
        if (measureFrom.getUnit().getSubtype() != null
                && unitTo.getSubtype() != null
                && !measureFrom.getUnit().getSubtype().equals(unitTo.getSubtype()))
            subtypeCoefficient = getSubtypeCoefficient(measureFrom.getUnit(), unitTo);

        //сконвертировали исходное Measure в primary исходного типа
        Measure primaryMeasureFrom = converterUtil(measureFrom, unitRepository.getBySubtypeAndIsPrimaryIsTrue(measureFrom.getUnit().getSubtype()));

        //и умножили его на коэффициент из отношения подтипов, полученный выше.
        //Тем самым мы перевели исходное значение value в primary другого типа.
        Measure primaryMeasureTo = new Measure(primaryMeasureFrom.getValue().multiply(subtypeCoefficient),
                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitTo.getSubtype()));

        //Получили primary Unit другого типа и сконвертировали valueTo с прошлого шага в целевой Unit.
        return converterUtil(primaryMeasureTo, unitTo);
    }

    public Measure convert(BigDecimal value, Unit unitFrom, Unit unitTo) {
        Measure inputMeasure = new Measure(value, unitFrom);
        return convert(inputMeasure, unitTo);
    }

    protected void isTheSameType(Measure measureFrom, Unit unitTo) {
        Unit unitFrom = measureFrom.getUnit();

        if (unitFrom.getType() != unitTo.getType())
            throw new IllegalUnitException("Conversion is not possible. Units has different types.");
    }

    protected BigDecimal getSubtypeCoefficient(Unit unitFrom, Unit unitTo) {
        String subtypeFrom = unitFrom.getSubtype();
        String subtypeTo = unitTo.getSubtype();

        Optional<RelationInType> relationInType1 = relationInTypeRepository.findById(new Relation(subtypeFrom, subtypeTo));
        BigDecimal subtypeCoefficient;
        if (relationInType1.isPresent()) {
            subtypeCoefficient = relationInType1.get().getSubtypeCoefficient();
        } else {
            Optional<RelationInType> relationInType2 = relationInTypeRepository.findById(new Relation(subtypeTo, subtypeFrom));
            if (relationInType2.isPresent()) {
                subtypeCoefficient = BigDecimal.valueOf(1)
                        .divide(relationInType2.get().getSubtypeCoefficient(), 10, RoundingMode.HALF_UP);
            } else throw new IllegalMeasureException("Conversion is not possible. Units has same types, but relation between subtypes is not define.");
        }
        return subtypeCoefficient;
    }

    protected Measure converterUtil(Measure measureFrom, Unit unitTo) {
        BigDecimal valueFrom = measureFrom.getValue();
        Unit unitFrom = measureFrom.getUnit();

        BigDecimal valueTo = valueFrom
                .multiply(unitFrom.getCoefficient())
                .divide(unitTo.getCoefficient(), 10, RoundingMode.HALF_UP)
                .stripTrailingZeros();

        measureFrom.setValue(valueTo);
        measureFrom.setUnit(unitTo);
        return measureFrom;
    }

    protected Measure converterUtil(BigDecimal value, Unit unitFrom, Unit unitTo) {
        Measure primary = new Measure(value, unitFrom);
        return converterUtil(primary, unitTo);
    }

    public UnitType getType() {
        return converterType;
    }
}
