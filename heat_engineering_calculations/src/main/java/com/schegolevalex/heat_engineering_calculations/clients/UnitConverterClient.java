package com.schegolevalex.heat_engineering_calculations.clients;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.units.Unit;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitConverterClient {
    @Value("${unit_converter.URL}")
    static String unitConverterURL;

    @Value("${unit_converter.convert-path-segment}")
    static String convertPathSegment;

    @Value("${unit_converter.convert-to-primary-path-segment}")
    static String convertToPrimaryPathSegment;

    @Value("${unit_converter.unit-path-segment}")
    static String unitPathSegment;

    final RestTemplate restTemplate;

    @Autowired
    public UnitConverterClient(RestTemplate restTemplate) {
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
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<String>("body", headers);

        ResponseEntity<Measure> responseEntity = restTemplate
                .exchange("http://localhost:8080/convert?value={value}&from={unitFrom}&to={unitTo}",
                        HttpMethod.GET, httpEntity, Measure.class, parameters);
        Measure body = responseEntity.getBody();

        return body;
    }

    public Unit getUnit(String id) {
        return restTemplate.getForObject(unitConverterURL + unitPathSegment, Unit.class);
    }
}
