package com.schegolevalex.boilerhouse.unit_library.config.serdeser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.NominalDiameter;

import java.io.IOException;

public class PipeNominalDiameterDeserializer extends JsonDeserializer<NominalDiameter> {

    @Override
    public NominalDiameter deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();

        for (NominalDiameter diameter : NominalDiameter.values()) {
            if (diameter.name().equalsIgnoreCase(name)) {
                return diameter;
            }
        }
        return null;
    }
}
