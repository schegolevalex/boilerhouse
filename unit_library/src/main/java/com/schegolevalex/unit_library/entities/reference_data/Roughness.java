package com.schegolevalex.unit_library.entities.reference_data;

import com.schegolevalex.unit_library.entities.pipes.PipeMaterial;
import java.math.BigDecimal;

public class Roughness {
    private BigDecimal value;
    private PipeMaterial pipeMaterial;

    public Roughness(BigDecimal value) {
        this.value = value;
    }

    public Roughness(PipeMaterial pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
        switch (pipeMaterial) {
            case STEEL: this.value = BigDecimal.valueOf(0.1);
            case METAL_PLASTIC: this.value = BigDecimal.valueOf(0.1);

        }
    }
}
