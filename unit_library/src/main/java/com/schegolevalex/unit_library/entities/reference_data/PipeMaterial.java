package com.schegolevalex.unit_library.entities.reference_data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.schegolevalex.unit_library.serdeser.PipeMaterialDeserializer;
import com.schegolevalex.unit_library.serdeser.PipeMaterialSerializer;

@JsonSerialize(using = PipeMaterialSerializer.class)
@JsonDeserialize(using = PipeMaterialDeserializer.class)
public enum PipeMaterial {
    STEEL,
    METAL_PLASTIC,
    COPPER,
    CAST_IRON,
    POLYPROPYLENE,
    XLPE;
}

