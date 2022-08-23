package unit_converter.entities.measures.constraints;

import lombok.Getter;
import unit_converter.entities.units.Unit;
import unit_converter.entities.units.UnitType;
import unit_converter.measure_converters.MeasureConverter;
import unit_converter.repositories.UnitRepository;

import java.math.BigDecimal;

@Getter
public abstract class MeasureConstraint {
    final MeasureConverter converter;
    final UnitRepository unitRepository;
    UnitType type;

    MeasureConstraint(MeasureConverter converter, UnitRepository unitRepository) {
        this.converter = converter;
        this.unitRepository = unitRepository;
    }

    public abstract void check(BigDecimal value, Unit unit);
}
