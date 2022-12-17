package com.schegolevalex.boilerhouse.pid.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
@Slf4j
public class GraphConverter implements AttributeConverter<Graph, String> {

    private final ObjectMapper mapper;

    @Autowired
    public GraphConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(Graph attribute) {
        String json = null;
        try {
            json = mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("GraphConverter.convertToDatabaseColumn: Couldn't writing JSON from {}", attribute.toString());
        }
        return json;
    }

    @Override
    public Graph<ElementType, DefaultEdge> convertToEntityAttribute(String json) {
        Graph<ElementType, DefaultEdge> graph = new DirectedMultigraph<ElementType, DefaultEdge>(DefaultEdge.class);
        try {
            mapper.readValue(json, new TypeReference<DirectedMultigraph<ElementType, DefaultEdge>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("GraphConverter.convertToEntityAttribute: Couldn't read JSON from {}", json);
        }
        return graph;
    }
}
