package com.schegolevalex.pid.models;

import com.schegolevalex.unit_library.models.measures.Measure;
import com.schegolevalex.unit_library.models.reference_data.NominalDiameter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id")
    Long id;

    @Column(name = "element_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    ElementType type;

    @OneToMany(mappedBy = "element")
    List<Port> ports;


    //    BOMDetails bomDetails;

    @Column("nominal_temperature")
    Measure nominalTemperature;

    @Column("nominal_diameter")
    @Enumerated(value = EnumType.STRING)
    NominalDiameter nominalDiameter;

    public Element(ElementType elementType) {
        this.type = elementType;
    }

}
