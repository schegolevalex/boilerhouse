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

    private final UnitType converterType;
    private final UnitRepository unitRepository;
    private final RelationInTypeRepository relationInTypeRepository;

    @Autowired
    public TemperatureConverter(UnitRepository unitRepository,
                                RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
        this.converterType = UnitType.TEMPERATURE;
    }

    @Override
    public Measure convert(Measure measureFrom, Unit unitTo) {

        Unit unitFrom = measureFrom.getUnit();

        if (unitFrom.getType() == unitTo.getType()) {
            Measure primaryMeasureFrom = convertToPrimary(measureFrom);

            String subtypeFrom = "distance_metric"; // todo подумать, как сделать нормально...
            String subtypeTo = "distance_imperial";
            Optional<RelationInType> relationInType1 = relationInTypeRepository.findById(new Relation(subtypeFrom, subtypeTo));
            BigDecimal subtypeCoefficient = null;
            if (relationInType1.isPresent()) {
                subtypeCoefficient = relationInType1.get().getSubtypeCoefficient();
            } else {
                Optional<RelationInType> relationInType2 = relationInTypeRepository.findById(new Relation(subtypeTo, subtypeFrom));
                if (relationInType2.isPresent()) {
                    subtypeCoefficient = new BigDecimal(1).divide(relationInType2.get().getSubtypeCoefficient());
                }
            }

            UnitType typeTo = unitTo.getType();
            Unit primaryUnitTo = unitRepository.getByTypeAndIsPrimaryIsTrue(typeTo);

            BigDecimal resultValue = primaryMeasureFrom.getValue().multiply(subtypeCoefficient);
            resultValue = resultValue.multiply(unitTo.getCoefficient()).divide(primaryUnitTo.getCoefficient());

            measureFrom.setValue(resultValue);
            measureFrom.setUnit(unitTo);
            return measureFrom;
        } else throw new
                IllegalUnitException("Конвертация невозможна. Единицы измерения разного типа.");
    }

    @Override
    public Measure convert(BigDecimal value, Unit unitFrom, Unit unitTo) {
        Measure inputMeasure = new Measure(value, unitFrom);
        return convert(inputMeasure, unitTo);
    }

    @Override
    public Measure convertToPrimary(Measure measure) {
        BigDecimal value = measure.getValue();
        Unit unitFrom = measure.getUnit();
        UnitType type = unitFrom.getType();
        Unit primaryUnit = unitRepository.getByTypeAndIsPrimaryIsTrue(type);

        BigDecimal primaryValue = value.multiply(unitFrom.getCoefficient()).divide(primaryUnit.getCoefficient());

        measure.setValue(primaryValue);
        measure.setUnit(primaryUnit);
        return measure;
    }

    @Override
    public Measure convertToPrimary(BigDecimal value, Unit unit) {
        Measure primary = new Measure(value, unit);
        return convertToPrimary(primary);
    }
}
