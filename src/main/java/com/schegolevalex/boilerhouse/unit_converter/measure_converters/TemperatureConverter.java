package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.Relation;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.RelationInType;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.boilerhouse.unit_converter.repositories.RelationInTypeRepository;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TemperatureConverter extends MeasureConverter {

    @Autowired
    public TemperatureConverter(UnitRepository unitRepository,
                                RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
        this.converterType = UnitType.TEMPERATURE;
    }

    @Override
    public Measure convert(Measure measureFrom, Unit unitTo) {
//
//        Unit unitFrom = measureFrom.getUnit();
//
//        if (unitFrom.getType() != unitTo.getType())
//            throw new IllegalUnitException("Конвертация невозможна. Единицы измерения разного типа.");
//
//        Measure primaryMeasureFrom = convertToPrimary(measureFrom);
//
//        String subtypeFrom = unitFrom.getSubtype();
//        String subtypeTo = unitTo.getSubtype();
//        Optional<RelationInType> relationInType1 = relationInTypeRepository.findById(new Relation(subtypeFrom, subtypeTo));
//        BigDecimal subtypeCoefficient = null;
//        if (relationInType1.isPresent()) {
//            subtypeCoefficient = relationInType1.get().getSubtypeCoefficient();
//        } else {
//            Optional<RelationInType> relationInType2 = relationInTypeRepository.findById(new Relation(subtypeTo, subtypeFrom));
//            if (relationInType2.isPresent()) {
//                subtypeCoefficient = new BigDecimal(1).divide(relationInType2.get().getSubtypeCoefficient());
//            }
//        }
//
//        UnitType typeTo = unitTo.getType();
//        Unit primaryUnitTo = unitRepository.getByTypeAndIsPrimaryIsTrue(typeTo);
//
//        BigDecimal resultValue = primaryMeasureFrom.getValue().multiply(subtypeCoefficient);
//        resultValue = resultValue.multiply(unitTo.getCoefficient()).divide(primaryUnitTo.getCoefficient());
//
//        measureFrom.setValue(resultValue);
//        measureFrom.setUnit(unitTo);
        return measureFrom;
    }
}
