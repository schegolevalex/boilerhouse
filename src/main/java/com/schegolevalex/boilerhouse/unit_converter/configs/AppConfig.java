package com.schegolevalex.boilerhouse.unit_converter.configs;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<Enum, Double> enumsMap () {
        EnumSet<DistanceMetric> enumSet = EnumSet.allOf(DistanceMetric.class);

        HashMap<Enum, Double> enumsMap = new HashMap<>();

        enumSet.forEach(e -> enumsMap.put(e, e.getCoefficient()));

        return enumsMap;
    }
}
