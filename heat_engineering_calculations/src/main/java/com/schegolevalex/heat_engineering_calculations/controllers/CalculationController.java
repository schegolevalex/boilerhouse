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
    public Measure getFlowRateByMass(@RequestBody Map<String,Measure> measures) {
        System.out.println(measures);
        return null;
//        return calculation.getFlowRateByMass(power, temperatureLow, temperatureHigh);
    }

    @PostMapping("/flow-rate-by-volume")
    public Measure getFlowRateByVolume(@RequestBody Measure power,
                                       @RequestBody Measure temperatureLow,
                                       @RequestBody Measure temperatureHigh) {
        return calculation.getFlowRateByVolume(power, temperatureLow, temperatureHigh);
    }
}
