package com.schegolevalex.boilerhouse.pid.models;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.GraphImporter;

import javax.persistence.AttributeConverter;
import java.io.*;

//@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GraphConverter implements AttributeConverter<Graph<ElementType, DefaultEdge>, String> {

    GraphExporter<ElementType, DefaultEdge> jsonElementTypeGraphExporter;
    GraphImporter<ElementType, DefaultEdge> jsonElementTypeImporter;

    public GraphConverter(GraphExporter<ElementType, DefaultEdge> jsonElementTypeGraphExporter,
                          GraphImporter<ElementType, DefaultEdge> jsonElementTypeImporter) {
        this.jsonElementTypeGraphExporter = jsonElementTypeGraphExporter;
        this.jsonElementTypeImporter = jsonElementTypeImporter;
    }

    @Override
    public String convertToDatabaseColumn(Graph<ElementType, DefaultEdge> graph) {
        String json = null;
        try (Writer writer = new StringWriter()) {
            jsonElementTypeGraphExporter.exportGraph(graph, writer);
            json = writer.toString();
        } catch (IOException e) {
            log.error("GraphConverter.convertToDatabaseColumn: Couldn't writing JSON from graph");
        }
        return json;
    }

    @Override
    public Graph<ElementType, DefaultEdge> convertToEntityAttribute(String json) {
        Graph<ElementType, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);
        try (Reader reader = new StringReader(json)) {
            jsonElementTypeImporter.importGraph(graph, reader);
        } catch (IOException e) {
            log.error("GraphConverter.convertToEntityAttribute: Couldn't read JSON from {}", json);
        }
        return graph;
    }
}
