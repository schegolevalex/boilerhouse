package com.schegolevalex.library.entities.relations_in_type;

import com.schegolevalex.unit_converter.entities.units.UnitType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "relation_in_type")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationInType {
    @EmbeddedId
    Relation relation;

    @Column(name = "coefficient", precision = 20, scale = 10)
    BigDecimal subtypeCoefficient;

    @Column(name = "type")
    UnitType type;
}
