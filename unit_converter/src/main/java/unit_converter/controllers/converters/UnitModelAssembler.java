package unit_converter.controllers.converters;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import unit_converter.controllers.ConverterController;
import unit_converter.entities.units.Unit;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UnitModelAssembler implements RepresentationModelAssembler<Unit, EntityModel<Unit>> {
    @Override
    public EntityModel<Unit> toModel(Unit unit) {
        return EntityModel.of(unit,
                linkTo(methodOn(ConverterController.class).getUnit(unit.getFullName())).withSelfRel(),
                linkTo(methodOn(ConverterController.class).getAllUnits()).withRel("units"));
    }
}
