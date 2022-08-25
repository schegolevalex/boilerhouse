package com.schegolevalex.boilerhouse.heat_engineering_calculations.clients;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitConverterClient {
    @Value("${unit_converter.URL}")
    String unitConverterURL;

    @Value("${unit_converter.convert-path-segment}")
    String convertPathSegment;

    @Value("${unit_converter.convert-to-primary-path-segment}")
    String convertToPrimaryPathSegment;

    @Value("${unit_converter.unit-path-segment}")
    String unitPathSegment;

    final RestTemplate restTemplate;

    @Autowired
    public UnitConverterClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Measure convertToPrimary(Measure measure) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));

        return restTemplate.getForEntity(unitConverterURL+convertToPrimaryPathSegment, Measure.class, parameters).getBody();
    }

    public Measure convert(Measure measure, Unit unitTo) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));
        parameters.put("unitTo", String.valueOf(unitTo));

        return restTemplate.getForEntity(unitConverterURL+convertPathSegment, Measure.class, parameters).getBody();
    }

    public Unit getUnit (String id) {
//        Unit unit = restTemplate.getForEntity(unitConverterURL + unitPathSegment, ArrayList.class);
        return null;
    }

    public List<Unit> getAllUnits () {
        ArrayList<Unit> allUnits = restTemplate.getForObject(unitConverterURL + unitPathSegment, ArrayList.class);
        return allUnits;
    }
}
