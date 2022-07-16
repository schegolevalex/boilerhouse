package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "temperature_unit")
@DiscriminatorValue(value = "temperature")
@NoArgsConstructor
public class Temperature extends Unit {

    public Temperature(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.TEMPERATURE, fullName, shortName, coefficient, isPrimary);
    }

    public Temperature(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.TEMPERATURE, fullName, shortName, new BigDecimal(coefficient), isPrimary);
    }
}
