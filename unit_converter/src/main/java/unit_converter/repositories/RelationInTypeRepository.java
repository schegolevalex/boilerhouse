package unit_converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unit_converter.entities.relations_in_type.Relation;
import unit_converter.entities.relations_in_type.RelationInType;

public interface RelationInTypeRepository extends JpaRepository<RelationInType, Relation> {
}
