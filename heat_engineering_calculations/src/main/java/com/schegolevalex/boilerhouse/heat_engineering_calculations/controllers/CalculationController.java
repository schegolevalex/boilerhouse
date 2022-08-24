package com.schegolevalex.boilerhouse.heat_engineering_calculations.controllers;

import com.schegolevalex.boilerhouse.heat_engineering_calculations.calculations.Calculation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.FlowRateByMass;

@RestController
public class CalculationController {

    final Calculation calculation;

    public CalculationController(Calculation calculation) {
        this.calculation = calculation;
    }

    @PostMapping("/flow-rate-by-mass")
    public FlowRateByMass getFlowRateByMass(@RequestBody Measure power,
                                            @RequestBody Measure temperatureLow,
                                            @RequestBody Measure temperatureHigh) {
        return calculation.getFlowRateByMass(power, temperatureLow, temperatureHigh);
    }

}
