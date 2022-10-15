package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.services.calculations.CalculationService;
import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.reference_data.PipeNominalDiameter;
import com.schegolevalex.unit_library.models.reference_data.PipeMaterial;
import com.schegolevalex.unit_library.models.reference_data.Roughness;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculationController {

    final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/flow-rate-by-mass")
    public Measure getFlowRateByMass(@RequestBody Map<String, Measure> request) {
        return calculationService.getFlowRateByMass(request.get("power"),
                request.get("temperatureLow"),
                request.get("temperatureHigh"));
    }

    @PostMapping("/flow-rate-by-volume")
    public Measure getFlowRateByVolume(@RequestBody Map<String, Measure> request) {
        return calculationService.getFlowRateByVolume(request.get("power"),
                request.get("temperatureLow"),
                request.get("temperatureHigh"));
    }

    @PostMapping("/specific-pressure-loss")
    public Measure getPressureLoss(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                   @JsonArg("kinematicViscosity") Measure kinematicViscosity,
                                   @JsonArg("roughness") Measure roughness,
                                   @JsonArg("pipeInnerDiameter") Measure pipeInnerDiameter) {
        return calculationService.getPressureLoss(flowRateByVolume,
                kinematicViscosity,
                roughness,
                pipeInnerDiameter);
    }

    @PostMapping("/reynolds-number")
    public BigDecimal getPressureLoss(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                      @JsonArg("kinematicViscosity") Measure kinematicViscosity,
                                      @JsonArg("pipeInnerDiameter") Measure pipeInnerDiameter) {
        return calculationService.getReynoldsNumber(flowRateByVolume, pipeInnerDiameter, kinematicViscosity);
    }

    @PostMapping("/speed")
    public Measure getSpeed(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                            @JsonArg("pipeInnerDiameter") Measure innerDiameter) {
        return calculationService.getSpeed(flowRateByVolume, innerDiameter);
    }

    @GetMapping("/pipeMaterials")
    public List<PipeMaterial> getPipeMaterials() {
        return Arrays.stream(PipeMaterial.values()).toList();
    }

    @GetMapping("/pipeNominalDiameters")
    public List<PipeNominalDiameter> getPipeNominalDiameters() {
        return Arrays.stream(PipeNominalDiameter.values()).toList();
    }

    @GetMapping("/roughnesses")
    public Map<PipeMaterial, Measure> getRoughness() {
        return Roughness.getRoughnessMap();
    }

    @PostMapping("/pipe-diameters-by-flow-rate-by-volume")
    public Map<PipeNominalDiameter, Pair<Measure, Measure>> getPipeNominalDiameter(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                                                                   @JsonArg("pipeMaterial") PipeMaterial pipeMaterial) {
        return calculationService.getPipeDiameter(flowRateByVolume, pipeMaterial);
    }
}
