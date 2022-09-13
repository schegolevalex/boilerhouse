package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.calculations.Calculation;
import com.schegolevalex.unit_library.entities.measures.Measure;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CalculationController {

    final Calculation calculation;

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
}
