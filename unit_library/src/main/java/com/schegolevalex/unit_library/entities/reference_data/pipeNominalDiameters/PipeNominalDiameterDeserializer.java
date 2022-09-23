package com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PipeNominalDiameterDeserializer extends JsonDeserializer<PipeNominalDiameter> {

    @Override
    public PipeNominalDiameter deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String pipeNominalDiameter = node.get("pipeNominalDiameter").asText();

        for (PipeNominalDiameter diameter : PipeNominalDiameter.values()) {
            if (diameter.name().equalsIgnoreCase(pipeNominalDiameter)) {
                return diameter;
            }
        }
        return null;
    }
}
