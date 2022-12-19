package com.schegolevalex.boilerhouse.pid.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.schegolevalex.boilerhouse.pid.controllers.utils.JsonArgumentResolver;
import com.schegolevalex.boilerhouse.pid.controllers.utils.StringToUnitConverter;
import com.schegolevalex.boilerhouse.pid.models.ElementType;
import com.schegolevalex.boilerhouse.unit_library.config.serdeser.PairSerializer;
import com.schegolevalex.boilerhouse.unit_library.config.serdeser.UnitDeserializer;
import com.schegolevalex.boilerhouse.unit_library.config.serdeser.UnitSerializer;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.json.JSONExporter;
import org.jgrapht.nio.json.JSONImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan({"com.schegolevalex.boilerhouse"})
public class AppConfig implements WebMvcConfigurer {

    private final StringToUnitConverter stringToUnitConverter;

    @Autowired
    public AppConfig(StringToUnitConverter stringToUnitConverter) {
        this.stringToUnitConverter = stringToUnitConverter;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Module addCustomUnitDeserializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Unit.class, new UnitDeserializer());
        return simpleModule;
    }

    @Bean
    public Module addCustomUnitSerializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Unit.class, new UnitSerializer());
        return simpleModule;
    }

    @Bean
    public Module addCustomPairSerializer() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Pair.class, new PairSerializer());
        return simpleModule;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUnitConverter);
    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }

    @Bean
    public GraphExporter<ElementType, DefaultEdge> jsonElementTypeGraphExporter() {
        JSONExporter<ElementType, DefaultEdge> exporter = new JSONExporter<>();
        exporter.setVertexIdProvider(Enum::name);
        return exporter;
    }

    @Bean
    public JSONImporter<ElementType, DefaultEdge> jsonElementTypeGraphImporter() {
        JSONImporter<ElementType, DefaultEdge> importer = new JSONImporter<>();
        importer.setVertexFactory(ElementType::valueOf);
        return importer;
    }
}