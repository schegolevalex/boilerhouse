package com.schegolevalex.boilerhouse.unit_library.config.serdeser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.schegolevalex.boilerhouse.unit_library.models.reference_data.PipeMaterial;

import java.io.IOException;

public class PipeMaterialSerializer extends JsonSerializer<PipeMaterial> {

    @Override
    public void serialize(PipeMaterial pipeMaterial, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("pipeMaterial");
        gen.writeString(pipeMaterial.name());
        gen.writeEndObject();
    }
}
