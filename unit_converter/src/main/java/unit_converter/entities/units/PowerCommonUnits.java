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
@Table(name = "power_common_units_unit")
@DiscriminatorValue(value = "power_common_units")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class PowerCommonUnits extends Unit {

    public PowerCommonUnits(String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(UnitType.POWER, fullName, shortName, coefficient, isPrimary);
        this.subtype = "power_common_units";
    }

    public PowerCommonUnits(String fullName, String shortName, double coefficient, Boolean isPrimary) {
        super(UnitType.POWER, fullName, shortName, BigDecimal.valueOf(coefficient), isPrimary);
        this.subtype = "power_common_units";
    }
}
