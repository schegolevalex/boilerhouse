package com.schegolevalex.heat_engineering_calculations.calculations;

import com.schegolevalex.heat_engineering_calculations.clients.UnitConverterClient;
import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.reference_data.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.reference_data.PipeMaterial;
import com.schegolevalex.unit_library.entities.reference_data.Roughness;
import com.schegolevalex.unit_library.entities.reference_data.Viscosity;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.exceptions.IllegalValueException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Calculation {

    final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);
    final BigDecimal WATER_DENSITY = BigDecimal.valueOf(1.0);
    final UnitConverterClient unitConverterClient;
    final MeasureFactory measureFactory;
    static MathContext mc = new MathContext(10, RoundingMode.HALF_UP);


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

    public Measure getSpeed(Measure flowRateByVolume,
                            Measure pipeInnerDiameter) {
        flowRateByVolume = unitConverterClient.convert(flowRateByVolume, Unit.METER_3_PER_HOUR);
        pipeInnerDiameter = unitConverterClient.convert(pipeInnerDiameter, Unit.MILLIMETER);
        return measureFactory.createMeasure(flowRateByVolume.getValue()
                        .multiply(BigDecimal.valueOf(1E6))
                        .divide(pipeInnerDiameter.getValue().pow(2), 10, RoundingMode.HALF_UP)
                        .divide(BigDecimal.valueOf(3600), 10, RoundingMode.HALF_UP)
                        .divide(BigDecimal.valueOf(0.785), 10, RoundingMode.HALF_UP)
                , Unit.METER_PER_SECOND);
    }

    public BigDecimal getReynoldsNumber(Measure flowRateByVolume,
                                        Measure pipeInnerDiameter,
                                        Measure kinematicViscosity) {
        pipeInnerDiameter = unitConverterClient.convert(pipeInnerDiameter, Unit.METER);
        return getSpeed(flowRateByVolume, pipeInnerDiameter).getValue()
                .multiply(pipeInnerDiameter.getValue())
                .divide(kinematicViscosity.getValue(), 10, RoundingMode.HALF_UP);
    }

    public Measure getPressureLoss(Measure flowRateByVolume,
                                   Measure kinematicViscosity,
                                   Measure roughness,
                                   Measure pipeInnerDiameter) {
        pipeInnerDiameter = unitConverterClient.convert(pipeInnerDiameter, Unit.METER);
        roughness = unitConverterClient.convert(roughness, Unit.METER);
        kinematicViscosity = unitConverterClient.convert(kinematicViscosity, Unit.SQUARE_METER_PER_SECOND);

        BigDecimal reynoldsNumber = getReynoldsNumber(flowRateByVolume, pipeInnerDiameter, kinematicViscosity);

        BigDecimal lambda;

        if (reynoldsNumber.compareTo(BigDecimal.valueOf(2320)) < 0) {
            // ламинарный режим
            lambda = BigDecimal.valueOf(64).divide(reynoldsNumber, mc);
        } else if (reynoldsNumber.compareTo(BigDecimal.valueOf(2320)) > -1
                && reynoldsNumber.compareTo(BigDecimal.valueOf(20)
                .multiply(pipeInnerDiameter.getValue())
                .divide(roughness.getValue(), mc)) < 0) {
            // турбулентный режим 1
            lambda = BigDecimal.valueOf(0.3164).divide(reynoldsNumber.sqrt(mc).sqrt(mc), mc);
        } else if (reynoldsNumber.compareTo(BigDecimal.valueOf(20)
                .multiply(pipeInnerDiameter.getValue())
                .divide(roughness.getValue(), mc)) > -1
                && reynoldsNumber.compareTo(BigDecimal.valueOf(500)
                .multiply(pipeInnerDiameter.getValue())
                .divide(roughness.getValue(), mc)) < 0) {
            // турбулентный режим 2
            BigDecimal term1 = roughness.getValue().divide(pipeInnerDiameter.getValue(), mc);
            BigDecimal term2 = BigDecimal.valueOf(68).divide(reynoldsNumber, mc);
            BigDecimal sqrt = term1.add(term2, mc).sqrt(mc).sqrt(mc);
            lambda = BigDecimal.valueOf(0.11).multiply(sqrt);
        } else {
            // турбулентный режим 3
            BigDecimal temp = roughness.getValue().divide(pipeInnerDiameter.getValue(), mc);
            BigDecimal sqrt = temp.sqrt(mc).sqrt(mc);
            lambda = BigDecimal.valueOf(0.11).multiply(sqrt);
        }
        Measure speed = getSpeed(flowRateByVolume, pipeInnerDiameter);
        BigDecimal value = lambda
                .multiply(speed.getValue().pow(2), mc)
                .divide(pipeInnerDiameter.getValue(), mc)
                .divide(BigDecimal.valueOf(2), mc)
                .divide(BigDecimal.valueOf(9.81), mc);

        return measureFactory.createMeasure(value, Unit.METER_OF_WATER);
    }

    public Map<PipeNominalDiameter, Pair<Measure, Measure>> getPipeDiameter(Measure flowRateByVolume, PipeMaterial pipeMaterial) {
        Measure convertedFlowRateByVolume = unitConverterClient.convert(flowRateByVolume, Unit.METER_3_PER_HOUR);

        Measure constraint = unitConverterClient.convert(measureFactory.createMeasure(30000, Unit.METER_3_PER_HOUR),
                flowRateByVolume.getUnit());
        if (convertedFlowRateByVolume.getValue().compareTo(BigDecimal.valueOf(30000)) > 0) {
            throw new IllegalValueException("Flow rate by volume must be less than " + constraint.getValue() + constraint.getUnit().getShortName());
        }

        Map<PipeNominalDiameter, Pair<Measure, Measure>> speedAndPressureLossMap = new HashMap<>();

        for (PipeNominalDiameter diameter : PipeNominalDiameter.values()) {
            Measure speed = getSpeed(flowRateByVolume, diameter.getDiameter());
            Measure pressureLoss = getPressureLoss(flowRateByVolume,
                    Viscosity.byTemperature(measureFactory.createMeasure(60, Unit.DEGREE_CELSIUS)),
                    Roughness.byPipeMaterial(pipeMaterial),
                    diameter.getDiameter());
            speedAndPressureLossMap.put(diameter, Pair.of(speed, pressureLoss));
        }
        return speedAndPressureLossMap.entrySet().stream()
                .filter(es -> es.getValue().getFirst().getValue().compareTo(BigDecimal.valueOf(1.5)) < 1
                        && es.getValue().getFirst().getValue().compareTo(BigDecimal.valueOf(0.8)) > -1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
