package com.schegolevalex.boilerhouse.heat_engineering_calculations.calculations;

import com.schegolevalex.boilerhouse.heat_engineering_calculations.clients.UnitConverterClient;
import com.schegolevalex.boilerhouse.heat_engineering_calculations.entities.ClientUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.measures.MeasureFactory;
import unit_converter.exceptions.IllegalUnitException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Calculation {

    final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);
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
        BigDecimal answer = fPowerValue.divide(delta, RoundingMode.HALF_UP).divide(HEAT_CAPACITY, RoundingMode.HALF_UP);

        return measureFactory.createMeasure(answer, findUnit("TONNE_PER_HOUR"));
    }

    private ClientUnit findUnit(String name) {
        List<ClientUnit> allClientUnits = unitConverterClient.getAllClientUnits();

        return allClientUnits.stream()
                .filter((clientUnit) -> clientUnit.getFullName() == name)
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit in database"));
    }
}
