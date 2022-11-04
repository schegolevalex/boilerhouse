package com.schegolevalex.unit_library.models.measures;

import com.schegolevalex.unit_library.models.units.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measure {

    BigDecimal value;

    Unit unit;

    public Measure(BigDecimal value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Measure(double value, Unit unit) {
        this.value = BigDecimal.valueOf(value);
        this.unit = unit;
    }
}
