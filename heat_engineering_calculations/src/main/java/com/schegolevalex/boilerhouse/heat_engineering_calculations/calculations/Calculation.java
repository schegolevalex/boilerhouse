package com.schegolevalex.boilerhouse.heat_engineering_calculations.calculations;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unit_converter.entities.units.FlowRateByMass;
import unit_converter.entities.units.Power;
import unit_converter.entities.units.Temperature;

@Component
@Getter
public class Calculation {

    private final RestTemplate restTemplate;

    public Calculation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FlowRateByMass getFlowRateByMass(Power power, Temperature temperature1, Temperature temperature2) {
        return null;
    }
}
