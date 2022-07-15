package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "distance_metric_unit")
@DiscriminatorValue(value = "distance_metric")
@NoArgsConstructor
public class DistanceMetric extends Unit {

    public DistanceMetric(String fullName, String shortName, BigDecimal coefficient, Boolean primary) {
        super(UnitType.DISTANCE, fullName, shortName, coefficient, primary);
    }

    public DistanceMetric(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.DISTANCE, fullName, shortName, new BigDecimal(coefficient), isPrimary);
    }
}
