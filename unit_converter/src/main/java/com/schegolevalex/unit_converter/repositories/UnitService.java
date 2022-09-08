package com.schegolevalex.unit_converter.repositories;


import com.schegolevalex.library.entities.units.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitService extends JpaRepository<Unit, String> {
    Unit getByFullName(String fullName);
    Unit getBySubtypeAndIsPrimaryIsTrue(String subType);
}
