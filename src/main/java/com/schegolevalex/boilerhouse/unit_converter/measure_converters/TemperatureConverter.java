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
    Measure convertToPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        Temperature temperatureFrom = (Temperature) unitFrom;
        Temperature temperatureTo = (Temperature) unitTo;

        BigDecimal valueTo = valueFrom
                .multiply(temperatureFrom.getCoefficient())
                .divide(temperatureTo.getCoefficient(), 10, RoundingMode.HALF_UP)
                .add(temperatureFrom.getTerm())
                .stripTrailingZeros();

        return new Measure(valueTo, unitTo);
    }

    @Override
    Measure convertFromPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
        Temperature temperatureFrom = (Temperature) unitFrom;
        Temperature temperatureTo = (Temperature) unitTo;

        BigDecimal valueTo = valueFrom
                .multiply(temperatureTo.getCoefficientFromPrimary())
                .divide(temperatureFrom.getCoefficientFromPrimary(), 10, RoundingMode.HALF_UP)
                .add(temperatureTo.getTermFromPrimary())
                .stripTrailingZeros();

        return new Measure(valueTo, unitTo);
    }
}
