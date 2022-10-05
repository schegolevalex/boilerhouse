package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.calculations.Calculation;
import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.reference_data.pipe_materials.PipeMaterial;
import com.schegolevalex.unit_library.entities.reference_data.roughness.Roughness;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/specific-pressure-loss")
    public Measure getPressureLoss(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                   @JsonArg("kinematicViscosity") Measure kinematicViscosity,
                                   @JsonArg("roughness") Measure roughness,
                                   @JsonArg("pipeInnerDiameter") Measure pipeInnerDiameter) {
        return calculation.getPressureLoss(flowRateByVolume,
                kinematicViscosity,
                roughness,
                pipeInnerDiameter);
    }

    @PostMapping("/reynolds-number")
    public BigDecimal getPressureLoss(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                                      @JsonArg("kinematicViscosity") Measure kinematicViscosity,
                                      @JsonArg("pipeInnerDiameter") Measure pipeInnerDiameter) {
        return calculation.getReynoldsNumber(flowRateByVolume, pipeInnerDiameter, kinematicViscosity);
    }

    @PostMapping("/speed")
    public Measure getSpeed(@JsonArg("flowRateByVolume") Measure flowRateByVolume,
                            @JsonArg("pipeInnerDiameter") Measure innerDiameter) {
        return calculation.getSpeed(flowRateByVolume, innerDiameter);
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

//    @PostMapping("/test")
//    public PipeNominalDiameter test(@JsonArg("pipeNominalDiameter") PipeNominalDiameter nominalDiameter) {
//        return nominalDiameter;
//    }
//
//    @PostMapping("/test2")
//    public Measure test(@JsonArg("flowRateByVolume") Measure flowRateByVolume) {
//        return flowRateByVolume;
//    }
//
//    @PostMapping("/test3")
//    public int test(@JsonArg("flowRateByVolume") int a) {
//        return a;
//    }
}
