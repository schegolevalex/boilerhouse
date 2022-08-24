package com.schegolevalex.boilerhouse.heat_engineering_calculations.calculations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.FlowRateByMass;
import unit_converter.entities.units.Unit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class Calculation {

    private final RestTemplate restTemplate;

    @Value("${unit_converter.URL}")
    String unitConverterURL;

    @Value("${unit_converter.convert-path-segment}")
    String convertPathSegment;

    @Value("${unit_converter.convert-to-primary-path-segment}")
    String convertToPrimaryPathSegment;

    private final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);

    @Autowired
    public Calculation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FlowRateByMass getFlowRateByMass(Measure power, Measure temperatureLow, Measure temperatureHigh) {
        Measure primaryPower = convertToPrimary(power);
        Measure primaryTemperatureLow = convertToPrimary(temperatureLow);
        Measure primaryTemperatureHigh = convertToPrimary(temperatureHigh);



        return null;
    }

    private Measure convertToPrimary(Measure measure) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));

        return restTemplate.getForEntity(unitConverterURL+convertToPrimaryPathSegment, Measure.class, parameters).getBody();
    }

    private Measure convert(Measure measure, Unit unitTo) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));
        parameters.put("unitTo", String.valueOf(unitTo));

        return restTemplate.getForEntity(unitConverterURL+convertPathSegment, Measure.class, parameters).getBody();
    }
}
