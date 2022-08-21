package unit_converter.entities.units;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "distance_metric_unit")
@DiscriminatorValue(value = "distance_metric")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class DistanceMetric extends Unit {

    public DistanceMetric(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.DISTANCE, fullName, shortName, coefficient, isPrimary);
        this.subtype = "distance_metric";
    }

    public DistanceMetric(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.DISTANCE, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "distance_metric";
    }
}
