package com.schegolevalex.heat_engineering_calculations.configs;

import com.schegolevalex.unit_library.entities.measures.MeasureFactory;
import com.schegolevalex.unit_library.entities.units.UnwrappingUnitSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
@ComponentScan({"com.schegolevalex.unit_converter"})
public class HeatEngineeringCalculationsApplicationConfig implements WebMvcConfigurer {

    final MeasureFactory measureFactory;

    public HeatEngineeringCalculationsApplicationConfig(MeasureFactory measureFactory) {
        this.measureFactory = measureFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public Module addCustomUnitDeserializer() {
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addDeserializer(Unit.class, new UnitDeserializer());
//        return simpleModule;
//    }
//
//    @Bean
//    public ObjectMapper mapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.registerModule(addCustomUnitDeserializer());
//    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().serializers(new UnwrappingUnitSerializer());
    }
}