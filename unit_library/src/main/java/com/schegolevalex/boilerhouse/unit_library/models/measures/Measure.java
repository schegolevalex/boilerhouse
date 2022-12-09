package com.schegolevalex.boilerhouse.unit_library.models.measures;

import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
@Access(AccessType.PROPERTY)
public class Measure {
    @Column(name = "value")
    BigDecimal value;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
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
