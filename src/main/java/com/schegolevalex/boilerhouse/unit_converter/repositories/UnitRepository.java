package com.schegolevalex.boilerhouse.unit_converter.repositories;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, String> {
    Unit getByFullName(String fullName);
    Unit getByTypeAndIsPrimaryIsTrue(UnitType unitType);
}
