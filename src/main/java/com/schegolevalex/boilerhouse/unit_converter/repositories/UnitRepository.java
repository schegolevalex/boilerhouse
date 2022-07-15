package com.schegolevalex.boilerhouse.unit_converter.repositories;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, String> {
    Unit getByFullName(String fullName);
    Unit getUnitByIsPrimaryTrue();
}
