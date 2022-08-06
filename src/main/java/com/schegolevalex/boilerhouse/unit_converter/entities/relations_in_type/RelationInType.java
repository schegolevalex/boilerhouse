package com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
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

    @Column(name = "coefficient")
    BigDecimal subtypeCoefficient;

    @Column(name = "type")
    UnitType type;
}
