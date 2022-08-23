package unit_converter.entities.units;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public abstract class FlowRateByVolume extends Unit {
    public FlowRateByVolume(UnitType unitType, String fullName, String shortName, BigDecimal coefficient, Boolean isPrimary) {
        super(unitType, fullName, shortName, coefficient, isPrimary);
    }
}
