package com.schegolevalex.boilerhouse.unit_converter.entities.measures;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measure {
    BigDecimal value;
    Unit unit;

    public Measure(String value, Unit unit) {
        this(new BigDecimal(value), unit);
    }
}
