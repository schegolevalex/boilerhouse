package com.schegolevalex.unit_converter.services;

import com.schegolevalex.unit_converter.repositories.UnitRepository;
import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit getByFullName(String fullName) {
        return unitRepository.getByFullName(fullName);
    }

    public Unit getBySubtypeAndIsPrimaryIsTrue(String subType) {
        return unitRepository.getBySubtypeAndIsPrimaryIsTrue(subType);
    }

    public Optional<Unit> findById(String id) {
        return unitRepository.findById(id);
    }

    public List<Unit> findAll(Sort sort) {
        return unitRepository.findAll(sort);
    }

    public void save(Unit unit) {
        unitRepository.save(unit);
    }
}
