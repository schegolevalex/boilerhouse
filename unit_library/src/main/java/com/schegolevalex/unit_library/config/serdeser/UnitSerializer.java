package com.schegolevalex.unit_library.config.serdeser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.schegolevalex.unit_library.models.units.Unit;

import java.io.IOException;

public class UnitSerializer extends JsonSerializer<Unit> {

    private final JsonSerializer<Unit> delegate = new UnwrappingUnitSerializer();

    @Override
    public void serialize(Unit unit, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        this.delegate.serialize(unit, gen, provider);
        gen.writeEndObject();
    }

    @Override
    public JsonSerializer<Unit> unwrappingSerializer(final NameTransformer nameTransformer) {
        return new UnwrappingUnitSerializer();
    }
}
