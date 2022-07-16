package com.schegolevalex.boilerhouse.unit_converter.entities.measures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schegolevalex.boilerhouse.unit_converter.entities.checkers.MeasureConstraint;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measure {
    BigDecimal value;
    Unit unit;
    @JsonIgnore
    List<MeasureConstraint> constraintsList;

    public Measure(BigDecimal value, Unit unit) {
        constraintsList.stream().filter(c-> c.getType()==unit.getType()).forEach(c->c.check(value, unit));
        this.value = value;
        this.unit = unit;
    }

    @Autowired
    public void setConstraintsList(List<MeasureConstraint> constraintsList) {
        this.constraintsList = constraintsList;
    }

    public Measure(String value, Unit unit) {
        this(new BigDecimal(value), unit);
    }
}
