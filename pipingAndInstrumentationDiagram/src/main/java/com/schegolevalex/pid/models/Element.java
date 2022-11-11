package com.schegolevalex.pid.models;

import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.reference_data.NominalDiameter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "elements")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "element_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    ElementType type;

    @OneToMany(mappedBy = "element")
    List<Port> ports;


    //    BOMDetails bomDetails;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name = "nominal_temperature_value")),
            @AttributeOverride(name="unit", column=@Column(name = "nominal_temperature_unit"))
    })
    Measure nominalTemperature;

    @Column(name = "nominal_diameter")
    @Enumerated(value = EnumType.STRING)
    NominalDiameter nominalDiameter;

    public Element() {
    }

    public Element(ElementType elementType) {
        this.type = elementType;
    }
}
