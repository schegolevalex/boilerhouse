package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    public Unit findByFullName(String fullName) {
        return Unit.valueOfFullName(fullName);
    }

    public Unit getBySubtypeAndIsPrimaryIsTrue(String subType) {
        return Unit.valueOfSubtypeAndIsPrimaryIsTrue(subType);
    }

    public List<Unit> findAll() {
        return Unit.findAll();
    }
}
