package com.schegolevalex.heat_engineering_calculations.calculations;

import com.schegolevalex.heat_engineering_calculations.clients.UnitConverterClient;
import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.pipes.PipeMaterial;
import com.schegolevalex.unit_library.entities.pipes.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.units.Unit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        BigDecimal fPowerValue = unitConverterClient.convert(power, Unit.MEGACALORIES_PER_HOUR).getValue();
        BigDecimal fTemperatureLowValue = unitConverterClient.convert(temperatureLow, Unit.DEGREE_CELSIUS).getValue();
        BigDecimal fTemperatureHighValue = unitConverterClient.convert(temperatureHigh, Unit.DEGREE_CELSIUS).getValue();

        BigDecimal delta = fTemperatureHighValue.subtract(fTemperatureLowValue);
        BigDecimal resultValue = fPowerValue.divide(delta, RoundingMode.HALF_UP).divide(HEAT_CAPACITY, RoundingMode.HALF_UP);

        return measureFactory.createMeasure(resultValue, Unit.TONNE_PER_HOUR);
    }

    public Measure getFlowRateByVolume(Measure power,
                                       Measure temperatureLow,
                                       Measure temperatureHigh) {
        Measure flowRateByMass = getFlowRateByMass(power, temperatureLow, temperatureHigh);
        BigDecimal resultValue = flowRateByMass.getValue().divide(WATER_DENSITY, RoundingMode.HALF_UP);
        return measureFactory.createMeasure(resultValue, Unit.METER_3_PER_HOUR);
    }

    public Measure getPressureLoss(Measure flowRateByMass,
                                   Measure temperature,
                                   Measure roughness,
                                   PipeMaterial pipeMaterial,
                                   Measure pipeInnerDiameter) {

        return measureFactory.createMeasure(13.0, Unit.MEGAWATT);
    }

    public Measure getSpeed(Measure flowRateByVolume, PipeNominalDiameter nominalDiameter) {
        flowRateByVolume = unitConverterClient.convert(flowRateByVolume, Unit.METER_3_PER_HOUR);
        return measureFactory.createMeasure(
        flowRateByVolume.getValue()
                .multiply(BigDecimal.valueOf(1E6))
                .divide(BigDecimal.valueOf(nominalDiameter.getDiameter()).pow(2), RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(3600), RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(0.785), RoundingMode.HALF_UP), Unit.METER_PER_SECOND);
    }
}
