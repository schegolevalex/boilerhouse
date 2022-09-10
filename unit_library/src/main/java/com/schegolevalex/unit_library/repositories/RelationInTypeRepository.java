package com.schegolevalex.unit_library.repositories;

import com.schegolevalex.unit_library.entities.relations_in_type.Relation;
import com.schegolevalex.unit_library.entities.relations_in_type.RelationInType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationInTypeRepository extends JpaRepository<RelationInType, Relation> {
}
