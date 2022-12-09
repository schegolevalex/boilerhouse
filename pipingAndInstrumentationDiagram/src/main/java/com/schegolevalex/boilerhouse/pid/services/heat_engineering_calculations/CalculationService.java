package com.schegolevalex.boilerhouse.pid.services.heat_engineering_calculations;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.NominalDiameter;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.PipeMaterial;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.Roughness;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.Viscosity;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalValueException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculationService {

    final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);
    final BigDecimal WATER_DENSITY = BigDecimal.valueOf(1.0);
    final UnitConverterService unitConverterService;
    final MeasureFactory measureFactory;
    static MathContext mc = new MathContext(10, RoundingMode.HALF_UP);


    @Autowired
    public CalculationService(UnitConverterService unitConverterService,
                              MeasureFactory measureFactory) {
        this.unitConverterService = unitConverterService;
        this.measureFactory = measureFactory;
    }

    public Measure getFlowRateByMass(Measure power,
                                     Measure temperatureLow,
                                     Measure temperatureHigh) {

        BigDecimal fPowerValue = unitConverterService.convert(power, Unit.MEGACALORIES_PER_HOUR).getValue();
        BigDecimal fTemperatureLowValue = unitConverterService.convert(temperatureLow, Unit.DEGREE_CELSIUS).getValue();
        BigDecimal fTemperatureHighValue = unitConverterService.convert(temperatureHigh, Unit.DEGREE_CELSIUS).getValue();

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
        flowRateByVolume = unitConverterService.convert(flowRateByVolume, Unit.METER_3_PER_HOUR);
        pipeInnerDiameter = unitConverterService.convert(pipeInnerDiameter, Unit.MILLIMETER);
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
        pipeInnerDiameter = unitConverterService.convert(pipeInnerDiameter, Unit.METER);
        return getSpeed(flowRateByVolume, pipeInnerDiameter).getValue()
                .multiply(pipeInnerDiameter.getValue())
                .divide(kinematicViscosity.getValue(), 10, RoundingMode.HALF_UP);
    }

    public Measure getPressureLoss(Measure flowRateByVolume,
                                   Measure kinematicViscosity,
                                   Measure roughness,
                                   Measure pipeInnerDiameter) {
        pipeInnerDiameter = unitConverterService.convert(pipeInnerDiameter, Unit.METER);
        roughness = unitConverterService.convert(roughness, Unit.METER);
        kinematicViscosity = unitConverterService.convert(kinematicViscosity, Unit.SQUARE_METER_PER_SECOND);

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

    public Map<NominalDiameter, Pair<Measure, Measure>> getPipeDiameter(Measure flowRateByVolume, PipeMaterial pipeMaterial) {
        Measure convertedFlowRateByVolume = unitConverterService.convert(flowRateByVolume, Unit.METER_3_PER_HOUR);

        Measure constraint = unitConverterService.convert(measureFactory.createMeasure(30000, Unit.METER_3_PER_HOUR),
                flowRateByVolume.getUnit());
        if (convertedFlowRateByVolume.getValue().compareTo(BigDecimal.valueOf(30000)) > 0) {
            throw new IllegalValueException("Flow rate by volume must be less than " + constraint.getValue() + constraint.getUnit().getShortName());
        }

        Map<NominalDiameter, Pair<Measure, Measure>> speedAndPressureLossMap = new HashMap<>();

        for (NominalDiameter diameter : NominalDiameter.values()) {
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
