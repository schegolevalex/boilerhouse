package com.schegolevalex.unit_library.entities.reference_data.pipes;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.reference_data.Element;
import com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.reference_data.pipe_materials.PipeMaterial;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class Pipe implements Element {
    final PipeMaterial material;
    final PipeNominalDiameter nominalDiameter;
    final BigDecimal outerDiameter;
    final BigDecimal wallThickness;
    final BigDecimal innerDiameter;
    final String standard;
    final Measure linearWeight;

    static final Map<String, Pipe> PIPE_MAP = new HashMap<>();

    static {
//        PIPE_MAP.put("")
    }

    public Pipe(PipeMaterial material,
                PipeNominalDiameter nominalDiameter,
                BigDecimal outerDiameter,
                BigDecimal wallThickness,
                BigDecimal innerDiameter,
                String standard,
                Measure linearWeight) {
        this.material = material;
        this.nominalDiameter = nominalDiameter;
        this.wallThickness = wallThickness;
        this.outerDiameter = outerDiameter;
        this.innerDiameter = innerDiameter;
        this.standard = standard;
        this.linearWeight = linearWeight;
    }


}
