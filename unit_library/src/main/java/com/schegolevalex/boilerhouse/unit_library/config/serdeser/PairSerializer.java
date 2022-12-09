package com.schegolevalex.boilerhouse.unit_library.config.serdeser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.util.Pair;

import java.io.IOException;

public class PairSerializer extends JsonSerializer<Pair> {

    @Override
    public void serialize(Pair pair, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("speed", pair.getFirst());
        gen.writeObjectField("pressure_loss", pair.getSecond());
        gen.writeEndObject();
    }
}
