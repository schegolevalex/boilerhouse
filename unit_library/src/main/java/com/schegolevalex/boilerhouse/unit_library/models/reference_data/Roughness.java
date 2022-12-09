package com.schegolevalex.boilerhouse.unit_library.models.reference_data;

import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Roughness {
    private static Map<PipeMaterial, Measure> roughnessMap = new HashMap<>();

    static {
        roughnessMap.put(PipeMaterial.STEEL, new Measure(0.1, Unit.MILLIMETER));
        roughnessMap.put(PipeMaterial.METAL_PLASTIC, new Measure(0.01, Unit.MILLIMETER));
        roughnessMap.put(PipeMaterial.COPPER, new Measure(0.01, Unit.MILLIMETER));
        roughnessMap.put(PipeMaterial.CAST_IRON, new Measure(1, Unit.MILLIMETER));
        roughnessMap.put(PipeMaterial.POLYPROPYLENE, new Measure(0.01, Unit.MILLIMETER));
        roughnessMap.put(PipeMaterial.XLPE, new Measure(0.01, Unit.MILLIMETER));
    }

    public static Measure byPipeMaterial(PipeMaterial pipeMaterial) {
        return roughnessMap.get(pipeMaterial);
    }

    public static Map<PipeMaterial, Measure> getRoughnessMap() {
        return roughnessMap;
    }
}
