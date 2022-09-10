package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.repositories.RelationInTypeRepository;
import com.schegolevalex.unit_library.entities.relations_in_type.RelationInType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationInTypeService {
    private final RelationInTypeRepository relationInTypeRepository;

    @Autowired
    public RelationInTypeService(RelationInTypeRepository relationInTypeService) {
        this.relationInTypeRepository = relationInTypeService;
    }


    public void save(RelationInType relationInType) {
        relationInTypeRepository.save(relationInType);
    }
}
