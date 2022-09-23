package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.calculations.Calculation;
import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.reference_data.pipe_materials.PipeMaterial;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalculationController {

    final Calculation calculation;

    @Autowired
    public CalculationController(Calculation calculation) {
        this.calculation = calculation;
    }

    @PostMapping("/flow-rate-by-mass")
    public Measure getFlowRateByMass(@RequestBody Map<String, Measure> request) {
        return calculation.getFlowRateByMass(request.get("power"),
                request.get("temperatureLow"),
                request.get("temperatureHigh"));
    }

    @PostMapping("/flow-rate-by-volume")
    public Measure getFlowRateByVolume(@RequestBody Map<String, Measure> request) {
        return calculation.getFlowRateByVolume(request.get("power"),
                request.get("temperatureLow"),
                request.get("temperatureHigh"));
    }

    @PostMapping("/pressure-loss")
    public Measure getPressureLoss(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                   @JsonArg("temperature") Measure temperature,
                                   @JsonArg("roughness") Measure roughness,
                                   @JsonArg("pipeMaterial") PipeMaterial pipeMaterial,
                                   @JsonArg("pipeInnerDiameter") Measure pipeInnerDiameter) {
        return calculation.getPressureLoss(flowRateByVolume,
                temperature,
                roughness,
                pipeMaterial,
                pipeInnerDiameter);
    }

    @PostMapping("/speed")
    public Measure getSpeed(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                            @JsonArg("pipeNominalDiameter") PipeNominalDiameter nominalDiameter) {
        return calculation.getSpeed(flowRateByVolume, nominalDiameter);
    }

    @GetMapping("/pipeMaterials")
    public List<PipeMaterial> getPipeMaterials() {
        return Arrays.stream(PipeMaterial.values()).toList();
    }

    @GetMapping("/pipeNominalDiameters")
    public List<PipeNominalDiameter> getPipeNominalDiameters() {
        return Arrays.stream(PipeNominalDiameter.values()).toList();
    }
}
