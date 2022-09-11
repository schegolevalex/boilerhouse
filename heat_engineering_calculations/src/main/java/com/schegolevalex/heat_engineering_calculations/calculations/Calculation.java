package com.schegolevalex.heat_engineering_calculations.calculations;

import com.schegolevalex.heat_engineering_calculations.clients.UnitConverterClient;
import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import com.schegolevalex.unit_library.entities.units.Unit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Calculation {

    final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);
    final BigDecimal WATER_DENSITY = BigDecimal.valueOf(1.0);
    @Getter
    final UnitConverterClient unitConverterClient;
    @Getter
    final MeasureFactory measureFactory;

    @Autowired
    public Calculation(UnitConverterClient unitConverterClient,
                       MeasureFactory measureFactory) {
        this.unitConverterClient = unitConverterClient;
        this.measureFactory = measureFactory;
    }

    public Measure getFlowRateByMass(Measure power,
                                     Measure temperatureLow,
                                     Measure temperatureHigh) {

        BigDecimal fPowerValue = unitConverterClient.convert(power, findUnit("GIGACALORIES_PER_HOUR")).getValue();
        BigDecimal fTemperatureLowValue = unitConverterClient.convert(temperatureLow, findUnit("DEGREE_CELSIUS")).getValue();
        BigDecimal fTemperatureHighValue = unitConverterClient.convert(temperatureHigh, findUnit("DEGREE_CELSIUS")).getValue();

        BigDecimal delta = fTemperatureHighValue.subtract(fTemperatureLowValue);
        BigDecimal resultValue = fPowerValue.divide(delta, RoundingMode.HALF_UP).divide(HEAT_CAPACITY, RoundingMode.HALF_UP);

        return measureFactory.createMeasure(resultValue, findUnit("TONNE_PER_HOUR"));
    }

    private Unit findUnit(String name) {
        List<Unit> allUnits = unitConverterClient.getAllUnits();

        return allUnits.stream()
                .filter((clientUnit) -> clientUnit.getFullName() == name)
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit in database"));
    }

    public Measure getFlowRateByVolume(Measure power,
                                       Measure temperatureLow,
                                       Measure temperatureHigh) {
        Measure flowRateByMass = getFlowRateByMass(power, temperatureLow, temperatureHigh);
        BigDecimal resultValue = flowRateByMass.getValue().divide(WATER_DENSITY, RoundingMode.HALF_UP);
        return measureFactory.createMeasure(resultValue, findUnit("M3_PER_HOUR")); //TODO insert FlowRateByVolume units to the DB
    }
}