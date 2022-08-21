package unit_converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unit_converter.entities.units.Unit;

public interface UnitRepository extends JpaRepository<Unit, String> {
    Unit getByFullName(String fullName);
    Unit getBySubtypeAndIsPrimaryIsTrue(String subType);
}
