package com.schegolevalex.unit_library.services.serdeser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.schegolevalex.unit_library.models.reference_data.PipeMaterial;
import com.schegolevalex.unit_library.exceptions.IllegalUnitException;

import java.io.IOException;

public class PipeMaterialDeserializer extends JsonDeserializer<PipeMaterial> {

    @Override
    public PipeMaterial deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String pipeMaterial = node.asText();

        for (PipeMaterial p : PipeMaterial.values()) {
            if (p.name().equalsIgnoreCase(pipeMaterial)) {
                return p;
            }
        }
        throw new IllegalUnitException("No such pipe material");
    }
}
