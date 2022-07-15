package com.schegolevalex.boilerhouse.unit_converter.entities.measures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schegolevalex.boilerhouse.unit_converter.entities.checkers.MeasureChecker;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measure {
    BigDecimal value;
    Unit unit;
    @JsonIgnore
    MeasureChecker checker;

    public Measure(BigDecimal value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Measure(String value, Unit unit) {
        this(new BigDecimal(value), unit);
    }
}
