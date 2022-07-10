package com.schegolevalex.boilerhouse.unit_converter.entities;

import com.schegolevalex.boilerhouse.unit_converter.measure_units.Unit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Measure {
    double value;
    Unit unit;

    public Measure(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }
}
