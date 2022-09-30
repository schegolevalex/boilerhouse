package com.schegolevalex.unit_library.entities.reference_data.pipe_materials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
