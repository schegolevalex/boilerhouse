package com.schegolevalex.unit_converter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationInTypeService {
    private final com.schegolevalex.unit_converter.repositories.RelationInTypeService relationInTypeService;

    @Autowired
    public RelationInTypeService(com.schegolevalex.unit_converter.repositories.RelationInTypeService relationInTypeService) {
        this.relationInTypeService = relationInTypeService;
    }


}
