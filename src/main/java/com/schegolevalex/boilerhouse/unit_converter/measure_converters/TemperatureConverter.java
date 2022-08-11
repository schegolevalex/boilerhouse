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

//    @Override
//    public Measure convert(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {
//        //проверка на соответствие типов
//        isTheSameType(unitFrom, unitTo);
//
//        //получили коэффициент из отношения подтипов
//        BigDecimal subtypeCoefficient = BigDecimal.valueOf(1);
//        if (unitFrom.getSubtype() != null
//                && unitTo.getSubtype() != null
//                && !unitFrom.getSubtype().equals(unitTo.getSubtype()))
//            subtypeCoefficient = getSubtypeCoefficient(unitFrom, unitTo);
//
//        //сконвертировали исходное Measure в primary исходного типа
//        Measure primaryMeasureFrom = convertToPrimary(valueFrom,
//                unitFrom,
//                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitFrom.getSubtype()));
//
//        //и умножили его на коэффициент из отношения подтипов, полученный выше.
//        //Тем самым мы перевели исходное значение value в primary другого типа.
//        Measure primaryMeasureTo = new Measure(primaryMeasureFrom.getValue().multiply(subtypeCoefficient),
//                unitRepository.getBySubtypeAndIsPrimaryIsTrue(unitTo.getSubtype()));
//
//        //Cконвертировали primaryMeasureTo с прошлого шага в целевой Unit.
//        return convertFromPrimary(primaryMeasureTo, unitTo);
//    }

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
