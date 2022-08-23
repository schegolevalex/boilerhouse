package com.schegolevalex.boilerhouse.heat_engineering_calculations.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.entities.units.FlowRateByMass;
import unit_converter.entities.units.Power;
import unit_converter.entities.units.Temperature;

@RestController
public class CalculationController {

    @PostMapping("/flow_rate_by_mass")
    public FlowRateByMass getFlowRateByMass(@RequestBody Power power,
                                            @RequestBody Temperature temperature1,
                                            @RequestBody Temperature temperature2) {
        return null;
    }

}
