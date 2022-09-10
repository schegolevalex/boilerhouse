package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.entities.relations_in_type.Relation;
import com.schegolevalex.unit_library.entities.relations_in_type.RelationInType;
import com.schegolevalex.unit_library.entities.units.UnitType;
import com.schegolevalex.unit_library.repositories.RelationInTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class RelationInTypeService {
    private final RelationInTypeRepository relationInTypeRepository;

    @Autowired
    public RelationInTypeService(RelationInTypeRepository relationInTypeService) {
        this.relationInTypeRepository = relationInTypeService;
    }


    private void save(RelationInType relationInType) {
        relationInTypeRepository.save(relationInType);
    }

    @PostConstruct
    private void init() {
        save(new RelationInType(new Relation("distance_metric", "distance_imperial"), BigDecimal.valueOf(39.3700787), UnitType.DISTANCE));
        save(new RelationInType(new Relation("power_international_system", "power_common_units"), BigDecimal.valueOf(0.85984523), UnitType.POWER));
        save(new RelationInType(new Relation("flow_rate_by_mass_metric", "flow_rate_by_mass_imperial"), BigDecimal.valueOf(2204.62262), UnitType.FLOW_RATE_BY_MASS));
        save(new RelationInType(new Relation("mass_metric", "mass_imperial"), BigDecimal.valueOf(2204.62262), UnitType.MASS));
//        save(new RelationInType(new Relation("flow_rate_by_volume_metric", "flow_rate_by_volume_imperial"), BigDecimal.valueOf(), UnitType.FLOW_RATE_BY_VOLUME));
    }
}
