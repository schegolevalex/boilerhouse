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
@Table(name = "power_international_system_unit")
@DiscriminatorValue(value = "power_international_system")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class PowerInternationalSystem extends Power {

    public PowerInternationalSystem(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.POWER, fullName, shortName, coefficient, isPrimary);
        this.subtype = "power_international_system";
    }

    public PowerInternationalSystem(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.POWER, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "power_international_system";
    }
}
