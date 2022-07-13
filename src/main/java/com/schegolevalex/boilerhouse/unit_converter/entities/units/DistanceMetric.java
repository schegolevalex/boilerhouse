package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name ="distance_metric_unit")
@DiscriminatorValue(value = "distance_metric")
public class DistanceMetric extends Unit {

    public DistanceMetric(String fullName, String shortName, BigDecimal coefficient, Boolean primary) {
        super(UnitType.DISTANCE, fullName, shortName, coefficient, primary);
    }
}
