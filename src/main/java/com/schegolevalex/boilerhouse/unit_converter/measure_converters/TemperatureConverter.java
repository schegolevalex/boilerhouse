package com.schegolevalex.boilerhouse.unit_converter.measure_converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Temperature;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import com.schegolevalex.boilerhouse.unit_converter.repositories.RelationInTypeRepository;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    public Measure convertUtil(Measure measureFrom, Unit unitTo) {
        BigDecimal valueFrom = measureFrom.getValue();
        Temperature temperatureUnitFrom = (Temperature) measureFrom.getUnit();
        Temperature temperatureUnitTo = (Temperature) unitRepository.getBySubtypeAndIsPrimaryIsTrue(temperatureUnitFrom.getSubtype());

        BigDecimal valueTo = valueFrom
                .multiply(temperatureUnitFrom.getCoefficient())
                .divide(temperatureUnitTo.getCoefficient(), 10, RoundingMode.HALF_UP)
                .subtract(temperatureUnitFrom.getTerm())
                .stripTrailingZeros();

        measureFrom.setValue(valueTo);
        measureFrom.setUnit(temperatureUnitTo);
        return measureFrom;
    }
}
