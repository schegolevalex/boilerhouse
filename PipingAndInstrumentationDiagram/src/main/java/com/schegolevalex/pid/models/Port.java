package com.schegolevalex.pid.models;

import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.reference_data.NominalDiameter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table("ports")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Port {
    @Id
    @Column("id")
    Long id;

    @Column("nominal_diameter")
    @Enumerated(EnumType.STRING)
    NominalDiameter nominalDiameter;

    @Column("nominal_pressure")
    Measure nominalPressure;

    @Column("connection_type")
    @Enumerated(EnumType.STRING)
    ConnectionType connectionType;

    @Column("flow_rate_by_mass")
    Measure flowRateByMass;

    @Column("fact_pressure")
    Measure factPressure;

    @ManyToOne
    @JoinColumn(name = "element_id", nullable = false)
    Element element;
}
