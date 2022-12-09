package com.schegolevalex.boilerhouse.unit_library.services;

import com.schegolevalex.boilerhouse.unit_library.models.units.SubtypeRelationInType;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.stereotype.Service;

@Service
public class SubtypeRelationInTypeService {
    public SubtypeRelationInType getByRelationBySubtypes (String subtype1, String subtype2) {
        return SubtypeRelationInType.valueOfSubtypes(subtype1, subtype2);
    }

    public SubtypeRelationInType getByRelationBySubtypes (Unit unit1, Unit unit2) {
        return getByRelationBySubtypes(unit1.getSubtype(), unit2.getSubtype());
    }
}
