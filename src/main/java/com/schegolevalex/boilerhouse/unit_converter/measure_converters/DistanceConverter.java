package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.boilerhouse.unit_converter.repositories.RelationInTypeRepository;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DistanceConverter implements MeasureConverter {

    private final UnitType converterType;
    private final UnitRepository unitRepository;
    private final RelationInTypeRepository relationInTypeRepository;

    @Autowired
    public DistanceConverter(UnitRepository unitRepository,
                             RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
        this.converterType = UnitType.DISTANCE;
    }

    @Override
    public Measure convert(Measure measure, Unit unitTo) {

        Unit unitFrom = measure.getUnit();

        if (unitFrom.getType() == unitTo.getType()) {
            Measure primaryMeasureFrom = convertToPrimary(measure);
            relationInTypeRepository.findBy


            BigDecimal resultValue = measure.getValue()
                    .multiply(measure.getUnit().getCoefficient())
                    .divide(unitTo.getCoefficient());




            measure.setValue(resultValue);
            measure.setUnit(unitTo);
            return measure;
        } else throw new IllegalUnitException("Конвертация невозможна. Единицы измерения разного типа.");
    }

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

    public Measure convertToPrimary(BigDecimal value, Unit unit) {
        Measure primary = new Measure(value, unit);
        return convertToPrimary(primary);
    }

    @Override
    public UnitType getType() {
        return converterType;
    }
}
