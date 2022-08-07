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
    public Measure convert(Measure temperatureFrom, Unit unitTo) {
        Temperature temperatureUnitTo = (Temperature) unitTo;

        //проверка на соответствие типов
        isTheSameType(temperatureFrom, temperatureUnitTo);

        //получили коэффициент из отношения подтипов
        BigDecimal subtypeCoefficient = BigDecimal.valueOf(1);
        if (temperatureFrom.getUnit().getSubtype() != null
                && temperatureUnitTo.getSubtype() != null
                && !temperatureFrom.getUnit().getSubtype().equals(temperatureUnitTo.getSubtype())) {
            subtypeCoefficient = getSubtypeCoefficient(temperatureFrom.getUnit(), temperatureUnitTo);
        }

        //сконвертировали исходное Measure в primary и умножили его на коэффициент из отношения подтипов, полученный выше.
        //Тем самым мы перевели исходное значение value в primary другого типа.
        Measure primaryTemperatureFrom = convertToPrimary(temperatureFrom);
        BigDecimal resultValue = primaryTemperatureFrom.getValue().multiply(subtypeCoefficient);

        //Получили primary Unit другого типа и сконвертировали resultValue с прошлого шага в целевой Unit.
        Temperature primaryTemperatureUnitTo = (Temperature) unitRepository.getBySubtypeAndIsPrimaryIsTrue(temperatureUnitTo.getSubtype());
        resultValue = resultValue
                .multiply(primaryTemperatureUnitTo.getCoefficient())
                .divide(temperatureUnitTo.getCoefficient(), 15, RoundingMode.HALF_UP)
                .subtract(primaryTemperatureUnitTo.getTerm())
                .stripTrailingZeros();

        temperatureFrom.setValue(resultValue);
        temperatureFrom.setUnit(temperatureUnitTo);
        return temperatureFrom;
    }

    @Override
    public Measure convertToPrimary(Measure measureFrom) {
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
