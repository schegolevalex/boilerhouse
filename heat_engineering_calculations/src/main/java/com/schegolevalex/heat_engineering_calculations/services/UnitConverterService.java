package com.schegolevalex.heat_engineering_calculations.services;

import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.units.Unit;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitConverterService {
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
    public UnitConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Measure convertToPrimary(Measure measure) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));

        return restTemplate
                .getForEntity(unitConverterURL + convertToPrimaryPathSegment, Measure.class, parameters)
                .getBody();
    }

    public Measure convert(Measure measure, Unit unitTo) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit().getFullName()));
        parameters.put("unitTo", String.valueOf(unitTo.getFullName()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);

        ResponseEntity<Measure> responseEntity = restTemplate
                .exchange(unitConverterURL + convertPathSegment,
                        HttpMethod.GET, httpEntity, Measure.class, parameters);

        return responseEntity.getBody();
    }

    public Unit getUnit(String id) {
        return restTemplate.getForObject(unitConverterURL + unitPathSegment + "/" + id, Unit.class);
    }
}
