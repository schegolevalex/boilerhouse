package com.schegolevalex.unit_converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schegolevalex.unit_converter.entities.relations_in_type.Relation;
import com.schegolevalex.unit_converter.entities.relations_in_type.RelationInType;

public interface RelationInTypeRepository extends JpaRepository<RelationInType, Relation> {
}
