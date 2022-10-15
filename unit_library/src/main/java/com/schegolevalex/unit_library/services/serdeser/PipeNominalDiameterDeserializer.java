package com.schegolevalex.unit_library.services.serdeser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.schegolevalex.unit_library.models.reference_data.PipeNominalDiameter;

import java.io.IOException;

public class PipeNominalDiameterDeserializer extends JsonDeserializer<PipeNominalDiameter> {

    @Override
    public PipeNominalDiameter deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();

        for (PipeNominalDiameter diameter : PipeNominalDiameter.values()) {
            if (diameter.name().equalsIgnoreCase(name)) {
                return diameter;
            }
        }
        return null;
    }
}
