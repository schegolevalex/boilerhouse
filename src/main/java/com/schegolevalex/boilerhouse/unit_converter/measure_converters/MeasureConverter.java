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

    public abstract Measure convert(Measure measure, Unit unitTo);

    public Measure convert(BigDecimal value, Unit unitFrom, Unit unitTo) {
        Measure inputMeasure = new Measure(value, unitFrom);
        return convert(inputMeasure, unitTo);
    }

    protected void isTheSameType(Measure measureFrom, Unit unitTo) {
        Unit unitFrom = measureFrom.getUnit();

        if (unitFrom.getType() != unitTo.getType())
            throw new IllegalUnitException("Конвертация невозможна. Единицы измерения разного типа.");
    }

    protected BigDecimal getSubtypeCoefficient(Measure measureFrom, Unit unitTo) {
        String subtypeFrom = measureFrom.getUnit().getSubtype();
        String subtypeTo = unitTo.getSubtype();

        Optional<RelationInType> relationInType1 = relationInTypeRepository.findById(new Relation(subtypeFrom, subtypeTo));
        BigDecimal subtypeCoefficient;
        if (relationInType1.isPresent()) {
            subtypeCoefficient = relationInType1.get().getSubtypeCoefficient();
        } else {
            Optional<RelationInType> relationInType2 = relationInTypeRepository.findById(new Relation(subtypeTo, subtypeFrom));
            if (relationInType2.isPresent()) {
                subtypeCoefficient = BigDecimal.valueOf(1).divide(relationInType2.get().getSubtypeCoefficient(), 10, RoundingMode.HALF_UP);
            } else throw new IllegalMeasureException("************Не удается произвести конвертацию.***********");
        }
        return subtypeCoefficient;
    }

    public Measure convertToPrimary(Measure measure) {
        BigDecimal value = measure.getValue();
        Unit unitFrom = measure.getUnit();
        Unit primaryUnit = unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitFrom.getSubtype());

        BigDecimal primaryValue = value.multiply(unitFrom.getCoefficient()).divide(primaryUnit.getCoefficient(), 10, RoundingMode.HALF_UP);

        measure.setValue(primaryValue);
        measure.setUnit(primaryUnit);
        return measure;
    }

    public Measure convertToPrimary(BigDecimal value, Unit unit) {
        Measure primary = new Measure(value, unit);
        return convertToPrimary(primary);
    }

    public UnitType getType() {
        return converterType;
    }
}
