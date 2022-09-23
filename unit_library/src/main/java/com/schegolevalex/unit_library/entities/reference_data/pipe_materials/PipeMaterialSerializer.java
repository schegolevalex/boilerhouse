package com.schegolevalex.unit_library.entities.reference_data.pipe_materials;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

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
