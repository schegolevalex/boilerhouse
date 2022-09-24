package com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PipeNominalDiameterSerializer extends JsonSerializer<PipeNominalDiameter> {

    @Override
    public void serialize(PipeNominalDiameter pipeNominalDiameter, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", pipeNominalDiameter.name());
        gen.writeNumberField("diameter", pipeNominalDiameter.getDiameter());
        gen.writeEndObject();
    }
}
