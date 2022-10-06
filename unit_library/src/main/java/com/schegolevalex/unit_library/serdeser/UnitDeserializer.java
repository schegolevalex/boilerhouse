package com.schegolevalex.unit_library.serdeser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.schegolevalex.unit_library.entities.units.Unit;

import java.io.IOException;

public class UnitDeserializer extends StdDeserializer<Unit> {

    public UnitDeserializer() {
        this(null);
    }

    public UnitDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Unit deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String unit = node.get("fullName").asText();

        for (Unit u : Unit.values()) {
            if (u.getFullName().equals(unit)) {
                return u;
            }
        }
        return null;
    }
}
