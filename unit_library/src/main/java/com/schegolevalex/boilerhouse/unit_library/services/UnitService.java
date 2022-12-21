package com.schegolevalex.boilerhouse.unit_library.services;

import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        return new ArrayList<>(Arrays.asList(Unit.values()));
    }

    public List<Unit> findByType(UnitType unitType) {
        return Unit.valueOfType(unitType);
    }
}
