package com.schegolevalex.unit_converter.measure_converters;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.relations_in_type.Relation;
import com.schegolevalex.unit_library.entities.relations_in_type.RelationInType;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.exceptions.IllegalMeasureException;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import com.schegolevalex.unit_library.repositories.RelationInTypeRepository;
import com.schegolevalex.unit_library.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
public class MeasureConverter {
    UnitType converterType = UnitType.DEFAULT;
    final UnitRepository unitRepository;
    final RelationInTypeRepository relationInTypeService;

    @Autowired
    public MeasureConverter(UnitRepository unitRepository, RelationInTypeRepository relationInTypeService) {
        this.unitRepository = unitRepository;
        this.relationInTypeService = relationInTypeService;
    }

    public Measure convert(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        //проверка на соответствие типов
        isTheSameType(unitFrom, unitTo);

        //получили коэффициент из отношения подтипов
        BigDecimal subtypeCoefficient = BigDecimal.valueOf(1);
        if (unitFrom.getSubtype() != null
                && unitTo.getSubtype() != null
                && !unitFrom.getSubtype().equals(unitTo.getSubtype()))
            subtypeCoefficient = getSubtypeCoefficient(unitFrom, unitTo);

        //сконвертировали исходное Measure в primary исходного типа
        Measure primaryMeasureFrom = convertToPrimary(valueFrom, unitFrom);

        //и умножили его на коэффициент из отношения подтипов, полученный выше.
        //Тем самым мы перевели исходное значение valueFrom в primary другого типа.
        Measure primaryMeasureTo = new Measure(primaryMeasureFrom.getValue().multiply(subtypeCoefficient),
                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitTo.getSubtype()));

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
        return new Measure(valueTo, unitTo);
    }

    public Measure convertToPrimary(BigDecimal valueFrom, Unit unitFrom) {
        return convertToPrimary(valueFrom,
                unitFrom,
                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitFrom.getSubtype()));
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

    void isTheSameType(Unit unitFrom, Unit unitTo) {
        if (unitFrom.getType() != unitTo.getType())
            throw new IllegalUnitException("Conversion is not possible. Units has different types.");
    }

    void isTheSameType(Measure measureFrom, Unit unitTo) {
        Unit unitFrom = measureFrom.getUnit();
        isTheSameType(unitFrom, unitTo);
    }

    BigDecimal getSubtypeCoefficient(Unit unitFrom, Unit unitTo) {
        String subtypeFrom = unitFrom.getSubtype();
        String subtypeTo = unitTo.getSubtype();

        Optional<RelationInType> relationInType1 = relationInTypeService.findById(new Relation(subtypeFrom, subtypeTo));
        BigDecimal subtypeCoefficient;
        if (relationInType1.isPresent()) {
            subtypeCoefficient = relationInType1.get().getSubtypeCoefficient();
        } else {
            Optional<RelationInType> relationInType2 = relationInTypeService.findById(new Relation(subtypeTo, subtypeFrom));
            if (relationInType2.isPresent()) {
                subtypeCoefficient = BigDecimal.valueOf(1)
                        .divide(relationInType2.get().getSubtypeCoefficient(), 10, RoundingMode.HALF_UP);
            } else
                throw new IllegalMeasureException("Conversion is not possible. Units has same types, but relation between subtypes is not define.");
        }
        return subtypeCoefficient;
    }

    public UnitType getType() {
        return converterType;
    }
}
