package com.schegolevalex.boilerhouse.heat_engineering_calculations.clients;

import com.schegolevalex.boilerhouse.heat_engineering_calculations.entities.ClientUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unit_converter.entities.measures.Measure;

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

    @Value("${unit_converter.clientUnit-path-segment}")
    String unitPathSegment;

    final RestTemplate restTemplate;

    @Getter
    List<ClientUnit> allClientUnits;

    @Autowired
    public UnitConverterClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Measure convertToPrimary(Measure measure) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));

        return restTemplate.getForEntity(unitConverterURL + convertToPrimaryPathSegment, Measure.class, parameters).getBody();
    }

    public Measure convert(Measure measure, ClientUnit clientUnitTo) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("value", String.valueOf(measure.getValue()));
        parameters.put("unitFrom", String.valueOf(measure.getUnit()));
        parameters.put("unitTo", String.valueOf(clientUnitTo));

        return restTemplate.getForEntity(unitConverterURL + convertPathSegment, Measure.class, parameters).getBody();
    }

    public ClientUnit getUnit(String id) {
        ClientUnit clientUnit = restTemplate.getForObject(unitConverterURL + unitPathSegment, ClientUnit.class);
        return null;
    }
//
//    @PostConstruct
//    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
//    public void setAllClientUnits() {
//        allClientUnits = restTemplate.getForObject(unitConverterURL + unitPathSegment, ArrayList.class);
//    }
}
