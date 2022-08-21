package unit_converter.repositories;

import unit_converter.entities.relations_in_type.Relation;
import unit_converter.entities.relations_in_type.RelationInType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationInTypeRepository extends JpaRepository<RelationInType, Relation> {
}
