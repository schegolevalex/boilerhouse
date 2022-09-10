package com.schegolevalex.unit_library.entities.units;

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
@Table(name = "mass_metric_unit")
@DiscriminatorValue(value = "mass_metric")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class MassMetric extends Distance {

    public MassMetric(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.MASS, fullName, shortName, coefficient, isPrimary);
        this.subtype = "mass_metric";
    }

    public MassMetric(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.MASS, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "mass_metric";
    }
}
