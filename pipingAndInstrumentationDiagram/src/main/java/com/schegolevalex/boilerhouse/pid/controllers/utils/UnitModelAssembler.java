package com.schegolevalex.boilerhouse.pid.controllers.utils;

import com.schegolevalex.boilerhouse.pid.controllers.ConverterController;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UnitModelAssembler implements RepresentationModelAssembler<Unit, EntityModel<Unit>> {
    @Override
    public EntityModel<Unit> toModel(Unit unit) {
        return EntityModel.of(unit,
                WebMvcLinkBuilder.linkTo(methodOn(ConverterController.class).getUnit(unit.name())).withSelfRel(),
                linkTo(methodOn(ConverterController.class).getAllUnits()).withRel("units"));
    }
}
