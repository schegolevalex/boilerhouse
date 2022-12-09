package com.schegolevalex.boilerhouse.pid.controllers;

import com.schegolevalex.boilerhouse.pid.controllers.utils.UnitModelAssembler;
import com.schegolevalex.boilerhouse.pid.services.unit_converter.ConverterProcessor;
import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/convert")
    public Measure convert(@RequestParam("value") BigDecimal value,
                           @RequestParam("from") Unit unitFrom,
                           @RequestParam("to") Unit unitTo) {
        return converterProcessor.getConvertedResult(value, unitFrom, unitTo);
    }

    @GetMapping(value = "/convert-all")
    public List<Measure> convert(@RequestParam("value") BigDecimal value,
                                 @RequestParam("from") Unit unitFrom) {
        return converterProcessor.getConvertedResultList(value, unitFrom);
    }

    @GetMapping("/convert-to-primary")
    public Measure convertToPrimary(@RequestParam("value") BigDecimal value,
                                    @RequestParam("from") Unit unitFrom) {
        return converterProcessor.getConvertedToPrimaryResult(value, unitFrom);
    }

    @GetMapping("/units/{fullName}")
    public EntityModel<Unit> getUnit(@PathVariable String fullName) {
        Unit unit = unitService.findByFullName(fullName);
        return assembler.toModel(unit);
    }

    @GetMapping("/units")
    public List<EntityModel<Unit>> getAllUnits() {
        return unitService
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Unit::getUnitType))
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
}
