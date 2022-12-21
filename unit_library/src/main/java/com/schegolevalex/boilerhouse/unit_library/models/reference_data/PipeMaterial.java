package com.schegolevalex.boilerhouse.unit_library.models.reference_data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalUnitException;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PipeMaterial {
    STEEL,
    METAL_PLASTIC,
    COPPER,
    CAST_IRON,
    POLYPROPYLENE,
    XLPE;

    @JsonProperty("name")
    private String getPipeMaterial() {
        return name();
    }

    @JsonCreator
    public static PipeMaterial valueOfName(@JsonProperty("name") String name) {
        return Arrays.stream(values())
                .filter(pipeMaterial -> pipeMaterial.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalUnitException("No such unit"));
    }
}

