package com.schegolevalex.unit_library.entities.units;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UnwrappingUnitSerializer extends StdSerializer<Unit> {
    public UnwrappingUnitSerializer(){
        this(Unit.class);
    }

    public UnwrappingUnitSerializer(Class<Unit> t) {
        super(t);
    }

    @Override
    public void serialize(Unit unit, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStringField("fullName",unit.getFullName());
        gen.writeStringField("unitType",unit.getUnitType().name());
        gen.writeStringField("subtype",unit.getSubtype());
        gen.writeStringField("shortName",unit.getShortName());
    }

    @Override
    public boolean isUnwrappingSerializer() {
        return true;
    }
}
