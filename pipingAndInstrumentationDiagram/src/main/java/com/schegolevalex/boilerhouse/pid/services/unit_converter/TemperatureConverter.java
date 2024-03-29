package com.schegolevalex.boilerhouse.pid.services.unit_converter;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import com.schegolevalex.boilerhouse.unit_library.services.SubtypeRelationInTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TemperatureConverter extends MeasureConverter {

    @Autowired
    public TemperatureConverter(SubtypeRelationInTypeService subtypeRelationInTypeService,
                                MeasureFactory measureFactory) {
        super(subtypeRelationInTypeService, measureFactory);
        this.converterType = UnitType.TEMPERATURE;
    }

    @Override
    Measure convertToPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {

        BigDecimal valueTo = valueFrom
                .multiply(unitFrom.getCoefficient())
                .divide(unitTo.getCoefficient(), 10, RoundingMode.HALF_UP)
                .add(unitFrom.getTerm())
                .stripTrailingZeros();

        return measureFactory.createMeasure(valueTo, unitTo);
    }

    @Override
    Measure convertFromPrimary(BigDecimal valueFrom, Unit unitFrom, Unit unitTo) {

        BigDecimal valueTo = valueFrom
                .multiply(unitTo.getCoefficientFromPrimary())
                .divide(unitFrom.getCoefficientFromPrimary(), 10, RoundingMode.HALF_UP)
                .add(unitTo.getTermFromPrimary())
                .stripTrailingZeros();

        return measureFactory.createMeasure(valueTo, unitTo);
    }
}
