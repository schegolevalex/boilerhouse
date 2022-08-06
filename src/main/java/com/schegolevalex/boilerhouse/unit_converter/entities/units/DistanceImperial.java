package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "distance_imperial_unit")
@DiscriminatorValue(value = "distance_imperial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class DistanceImperial extends Unit {

//    @Column(name = "subtype")
//    @JsonIgnore
//    String subtype;

    public DistanceImperial(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.DISTANCE, fullName, shortName, coefficient, isPrimary);
        this.subtype = "distance_imperial";
    }

    public DistanceImperial(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.DISTANCE, fullName, shortName, new BigDecimal(coefficient), isPrimary);
        this.subtype = "distance_imperial";
    }
}
