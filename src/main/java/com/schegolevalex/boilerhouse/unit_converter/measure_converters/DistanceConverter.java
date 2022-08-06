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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DistanceConverter extends MeasureConverter {

    @Autowired
    public DistanceConverter(UnitRepository unitRepository,
                             RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
        this.converterType = UnitType.DISTANCE;
    }

    @Override
    public Measure convert(Measure measureFrom, Unit unitTo) {

        isTheSameType(measureFrom, unitTo);

        //получили коэффициент из отношения подтипов
        BigDecimal subtypeCoefficient = new BigDecimal(1);
        if (!measureFrom.getUnit().getSubtype().equals(unitTo.getSubtype()))
            subtypeCoefficient = getSubtypeCoefficient(measureFrom, unitTo);

        //сконвертировали исходное Measure в primary и умножили его на коэффициент из отношения подтипов, полученный выше.
        //Тем самым мы перевели исходное значение value в primary другого типа.
        Measure primaryMeasureFrom = convertToPrimary(measureFrom);
        BigDecimal resultValue = primaryMeasureFrom.getValue().multiply(subtypeCoefficient);

        //Получили primary Unit другого типа и сконвертировали resultValue с прошлого шага в целевой Unit.
        Unit primaryUnitTo = unitRepository.getByTypeAndIsPrimaryIsTrue(unitTo.getType());
        resultValue = resultValue.multiply(unitTo.getCoefficient()).divide(primaryUnitTo.getCoefficient());

        measureFrom.setValue(resultValue);
        measureFrom.setUnit(unitTo);
        return measureFrom;
    }
}