package com.schegolevalex.boilerhouse.heat_engineering_calculations.controllers;

import com.schegolevalex.boilerhouse.heat_engineering_calculations.Answer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.entities.units.Power;
import unit_converter.entities.units.Temperature;

@RestController
public class CalculationController {

    @GetMapping("/flow_rate_by_mass")
    public Answer getFlowRateByMass (@RequestParam Power power,
                                     @RequestParam Temperature temperature1,
                                     @RequestParam Temperature temperature2
                                     ) {
        return null;
    }

}
