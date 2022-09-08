package com.schegolevalex.unit_converter.repositories;

import com.schegolevalex.library.entities.relations_in_type.Relation;
import com.schegolevalex.library.entities.relations_in_type.RelationInType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationInTypeService extends JpaRepository<RelationInType, Relation> {
}
