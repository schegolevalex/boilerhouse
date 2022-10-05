package com.schegolevalex.unit_library.entities.reference_data.pipes;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.reference_data.Element;
import com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters.PipeNominalDiameter;
import com.schegolevalex.unit_library.entities.reference_data.pipe_materials.PipeMaterial;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
@AllArgsConstructor
public class Pipe implements Element {
    final PipeMaterial material;
    final PipeNominalDiameter nominalDiameter;
    final BigDecimal outerDiameter;
    final BigDecimal wallThickness;
    final BigDecimal innerDiameter;
    final String standard;
    final Measure linearWeight;
}
