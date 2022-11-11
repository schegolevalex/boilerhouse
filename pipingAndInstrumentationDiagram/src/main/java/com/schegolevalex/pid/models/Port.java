package com.schegolevalex.pid.models;

import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.reference_data.NominalDiameter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "ports")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Port {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "nominal_diameter")
    @Enumerated(EnumType.STRING)
    NominalDiameter nominalDiameter;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name = "nominal_pressure_value")),
            @AttributeOverride(name="unit", column=@Column(name = "nominal_pressure_unit"))
    })
    Measure nominalPressure;

    @Column(name = "connection_type")
    @Enumerated(EnumType.STRING)
    ConnectionType connectionType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name = "flow_rate_by_mass_value")),
            @AttributeOverride(name="unit", column=@Column(name = "flow_rate_by_mass_unit"))
    })
    Measure flowRateByMass;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name = "fact_pressure_value")),
            @AttributeOverride(name="unit", column=@Column(name = "fact_pressure_unit"))
    })
    Measure factPressure;

    @ManyToOne
    @JoinColumn(name = "element_id", nullable = false)
    Element element;
}
