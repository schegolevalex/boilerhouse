package com.schegolevalex.boilerhouse.unit_converter.entities.measure;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measure {
    double value; // todo заменить на BigDecimal
    Unit unit;

    public Measure(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }
}
