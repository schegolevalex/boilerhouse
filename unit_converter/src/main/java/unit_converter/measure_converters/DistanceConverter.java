package unit_converter.measure_converters;

import unit_converter.entities.units.UnitType;
import unit_converter.repositories.RelationInTypeRepository;
import unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistanceConverter extends MeasureConverter {

    @Autowired
    public DistanceConverter(UnitRepository unitRepository,
                             RelationInTypeRepository relationInTypeRepository) {
        this.unitRepository = unitRepository;
        this.relationInTypeRepository = relationInTypeRepository;
        this.converterType = UnitType.DISTANCE;
    }
}
