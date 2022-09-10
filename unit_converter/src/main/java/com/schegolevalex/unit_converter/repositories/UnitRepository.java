package com.schegolevalex.unit_converter.repositories;


import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, String> {
    Unit getByFullName(String fullName);
    Unit getBySubtypeAndIsPrimaryIsTrue(String subType);
}
