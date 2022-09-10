package com.schegolevalex.unit_converter.controllers;

import com.schegolevalex.unit_library.entities.units.Unit;
import com.schegolevalex.unit_converter.controllers.converters.UnitModelAssembler;
import com.schegolevalex.unit_converter.entities.measures.Measure;
import com.schegolevalex.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.unit_converter.measure_converters.ConverterProcessor;
import com.schegolevalex.unit_converter.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ConverterController {

    private final ConverterProcessor converterProcessor;
    private final UnitService unitService;
    private final UnitModelAssembler assembler;

    @Autowired
    public ConverterController(ConverterProcessor converterProcessor,
                               UnitService unitService,
                               UnitModelAssembler assembler) {
        this.converterProcessor = converterProcessor;
        this.unitService = unitService;
        this.assembler = assembler;
    }

    @GetMapping("/convert")
    public Measure convert(@RequestParam("value") BigDecimal value,
                           @RequestParam("from") Unit unitFrom,
                           @RequestParam("to") Unit unitTo) {

        return converterProcessor.getConvertedResult(value, unitFrom, unitTo);
    }

    @GetMapping("/convert-to-primary")
    public Measure convertToPrimary(@RequestParam("value") BigDecimal value,
                                    @RequestParam("from") Unit unitFrom) {
        return converterProcessor.getConvertedResult(value, unitFrom);
    }

    @GetMapping("/units/{id}")
    public EntityModel<Unit> getUnit(@PathVariable String id) {
        Unit unit = unitService.findById(id).orElseThrow(() -> new IllegalUnitException("No such unit in database"));
        return assembler.toModel(unit);
    }

    @GetMapping("/units")
    public CollectionModel<EntityModel<Unit>> getAllUnits() {
        List<EntityModel<Unit>> units = unitService
                .findAll(Sort.by("type", "subtype").and(Sort.by(Sort.Direction.DESC, "isPrimary").and(Sort.by("fullName"))))
                .stream()
                .sorted((o1, o2) -> o1.getType().compareTo(o2.getType()))
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(units,
                linkTo(methodOn(ConverterController.class).getAllUnits()).withSelfRel());
    }
}
