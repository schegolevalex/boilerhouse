package com.schegolevalex.boilerhouse.unit_converter.entities.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "temperature_unit")
@DiscriminatorValue(value = "temperature")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Temperature extends Unit {

    @Column(name = "term")
    @JsonIgnore
    BigDecimal term;

    public Temperature(String fullName, String shortName, BigDecimal coefficient, BigDecimal term, Boolean isPrimary) {
        super(UnitType.TEMPERATURE, fullName, shortName, coefficient, isPrimary);
        this.term = term;
    }

    public Temperature(String fullName, String shortName, double coefficient, double term, Boolean isPrimary) {
        super(UnitType.TEMPERATURE, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.term = BigDecimal.valueOf(term);
    }
}
