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
@Table(name = "flow_rate_by_volume_imperial_unit")
@DiscriminatorValue(value = "flow_rate_by_volume_imperial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class FlowRateByVolumeImperial extends FlowRateByVolume {

    public FlowRateByVolumeImperial(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.FLOW_RATE_BY_VOLUME, fullName, shortName, coefficient, isPrimary);
        this.subtype = "flow_rate_by_volume_imperial";
    }

    public FlowRateByVolumeImperial(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.FLOW_RATE_BY_VOLUME, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "flow_rate_by_volume_imperial";
    }
}