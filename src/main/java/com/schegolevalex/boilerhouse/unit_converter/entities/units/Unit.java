package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@Table(name = "abstract_unit")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public abstract class Unit {
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    UnitType type;

    @Column(name = "subtype")
    @JsonIgnore
    protected String subtype = null;

    @Id
    @Column(name = "full_name")
    String fullName;

    @Column(name = "short_name")
    String shortName;

    @Column(name = "coefficient", nullable = false, precision = 30, scale = 15)
    @JsonIgnore
    BigDecimal coefficient;

    @Column(name = "is_primary", nullable = false)
    @JsonIgnore
    Boolean isPrimary;

    public Unit(UnitType type, String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        this.type = type;
        this.fullName = fullName;
        this.shortName = shortName;
        this.coefficient = coefficient;
        this.isPrimary = isPrimary;
    }
}
