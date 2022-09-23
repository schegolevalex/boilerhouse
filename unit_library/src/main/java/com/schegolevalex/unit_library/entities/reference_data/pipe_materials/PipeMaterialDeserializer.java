package com.schegolevalex.unit_library.entities.reference_data.pipe_materials;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PipeMaterialDeserializer extends JsonDeserializer<PipeMaterial> {

    @Override
    public PipeMaterial deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String pipeMaterial = node.get("pipeMaterial").asText();

        for (PipeMaterial p : PipeMaterial.values()) {
            if (p.name().equalsIgnoreCase(pipeMaterial)) {
                return p;
            }
        }
        return null;
    }
}
