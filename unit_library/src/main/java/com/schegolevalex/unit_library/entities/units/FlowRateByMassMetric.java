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
@Table(name = "flow_rate_by_mass_metric_unit")
@DiscriminatorValue(value = "flow_rate_by_mass_metric")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class FlowRateByMassMetric extends FlowRateByMass {

    public FlowRateByMassMetric(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.FLOW_RATE_BY_MASS, fullName, shortName, coefficient, isPrimary);
        this.subtype = "flow_rate_by_mass_metric";
    }

    public FlowRateByMassMetric(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.FLOW_RATE_BY_MASS, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "flow_rate_by_mass_metric";
    }
}
