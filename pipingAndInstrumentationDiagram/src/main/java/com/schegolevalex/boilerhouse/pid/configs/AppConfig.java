package com.schegolevalex.boilerhouse.pid.configs;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.schegolevalex.boilerhouse.pid.controllers.utils.JsonArgumentResolver;
import com.schegolevalex.boilerhouse.pid.controllers.utils.StringToUnitConverter;
import com.schegolevalex.boilerhouse.pid.models.ElementType;
import com.schegolevalex.boilerhouse.pid.serdeser.ElementTypeGraphSerializer;
import com.schegolevalex.boilerhouse.unit_library.serdeser.PairSerializer;
import org.jgrapht.Graph;
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
    public Module addCustomSerializersAndDeserializers() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Pair.class, new PairSerializer());
        simpleModule.addSerializer(Graph.class, new ElementTypeGraphSerializer());
        return simpleModule;
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUnitConverter);
    }

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