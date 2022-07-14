package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@Table(name = "abstract_unit")

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Unit {
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    UnitType type;

    @Id
    @Column(name = "full_name")
    String fullName;

    @Column(name = "short_name", nullable = true)
    String shortName;

    @Column(name = "coefficient", nullable = false)
    @JsonIgnore
    BigDecimal coefficient;

    @Column(name = "is_primary", nullable = false)
    @JsonIgnore
    Boolean isPrimary;
}
