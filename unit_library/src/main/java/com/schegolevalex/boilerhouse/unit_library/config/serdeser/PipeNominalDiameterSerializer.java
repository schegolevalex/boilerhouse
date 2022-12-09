package com.schegolevalex.boilerhouse.unit_library.config.serdeser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.NominalDiameter;

import java.io.IOException;

public class PipeNominalDiameterSerializer extends JsonSerializer<NominalDiameter> {

    @Override
    public void serialize(NominalDiameter nominalDiameter, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", nominalDiameter.name());
        provider.defaultSerializeField("diameter", nominalDiameter.getDiameter(), gen);
        gen.writeEndObject();
    }
}
