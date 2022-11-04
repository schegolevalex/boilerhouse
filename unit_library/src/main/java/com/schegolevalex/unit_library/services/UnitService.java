package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.models.units.Unit;
import com.schegolevalex.unit_library.models.units.UnitType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    public Unit findByFullName(String fullName) {
        return Unit.valueOfFullName(fullName);
    }

    public Unit findBySubtypeAndIsPrimaryIsTrue(String subType) {
        return Unit.valueOfSubtypeAndIsPrimaryIsTrue(subType);
    }

    public List<Unit> findAll() {
        return Unit.findAll();
    }

    public List<Unit> findByType(UnitType unitType) {
        return Unit.valueOfType(unitType);
    }
}
