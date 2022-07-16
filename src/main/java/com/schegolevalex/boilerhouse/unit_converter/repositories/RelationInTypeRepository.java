package com.schegolevalex.boilerhouse.unit_converter.repositories;

import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.Relation;
import com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type.RelationInType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationInTypeRepository extends JpaRepository<RelationInType, Relation> {
}
