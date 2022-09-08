package com.schegolevalex.unit_converter.services;

import com.schegolevalex.library.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    private final com.schegolevalex.unit_converter.repositories.UnitService unitRepository;

    @Autowired
    public UnitService(com.schegolevalex.unit_converter.repositories.UnitService unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit getByFullName(String fullName) {
        return unitRepository.getByFullName(fullName);
    }

    public Unit getBySubtypeAndIsPrimaryIsTrue(String subType) {
        return unitRepository.getBySubtypeAndIsPrimaryIsTrue(subType);
    }
}
