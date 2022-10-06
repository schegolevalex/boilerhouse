package com.schegolevalex.unit_library.entities.reference_data;

import com.schegolevalex.unit_library.entities.measures.Measure;
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
