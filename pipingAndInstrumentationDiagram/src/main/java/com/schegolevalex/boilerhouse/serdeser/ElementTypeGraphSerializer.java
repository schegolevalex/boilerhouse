package com.schegolevalex.boilerhouse.serdeser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.schegolevalex.boilerhouse.pid.models.ElementType;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.json.JSONExporter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

@Slf4j
public class ElementTypeGraphSerializer extends JsonSerializer<Graph> {

    private final JSONExporter<ElementType, DefaultEdge> jsonElementTypeGraphExporter = new JSONExporter<>();

    @Override
    public void serialize(Graph value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        jsonElementTypeGraphExporter.setVertexIdProvider(Enum::name);
        Writer writer = new StringWriter();
        jsonElementTypeGraphExporter.exportGraph(value, writer);
        gen.writeRawValue(writer.toString());
    }
}