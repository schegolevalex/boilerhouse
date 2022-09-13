package com.schegolevalex.unit_library.entities.measures;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_library.entities.units.UnitDeserializer;
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

    private static final long serialVersionUID = -7788619177798333712L;

    BigDecimal value;

    @JsonDeserialize(using = UnitDeserializer.class)
    Unit unit;

    public Measure(BigDecimal value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }
}
