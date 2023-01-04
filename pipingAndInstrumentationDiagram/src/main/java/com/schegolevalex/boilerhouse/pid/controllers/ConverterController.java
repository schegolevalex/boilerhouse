package com.schegolevalex.boilerhouse.pid.controllers;

import com.schegolevalex.boilerhouse.pid.controllers.utils.UnitModelAssembler;
import com.schegolevalex.boilerhouse.pid.services.unit_converter.ConverterService;
import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/converters")
public class ConverterController {

    private final ConverterService converterService;
    private final UnitModelAssembler assembler;

    @Autowired
    public ConverterController(ConverterService converterService,
                               UnitModelAssembler assembler) {
        this.converterService = converterService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/convert")
    public Measure convert(@RequestParam("value") BigDecimal value,
                           @RequestParam("from") Unit unitFrom,
                           @RequestParam("to") Unit unitTo) {
        return converterService.getConvertedResult(value, unitFrom, unitTo);
    }

    @GetMapping(value = "/convert-all")
    public List<Measure> convert(@RequestParam("value") BigDecimal value,
                                 @RequestParam("from") Unit unitFrom) {
        return converterService.getConvertedResultList(value, unitFrom);
    }

    @GetMapping("/convert-to-primary")
    public Measure convertToPrimary(@RequestParam("value") BigDecimal value,
                                    @RequestParam("from") Unit unitFrom) {
        return converterService.getConvertedToPrimaryResult(value, unitFrom);
    }

    @GetMapping("/units/{fullName}")
    public EntityModel<Unit> getUnit(@PathVariable String fullName) {
        Unit unit = Unit.valueOfFullName(fullName);
        return assembler.toModel(unit);
    }

    @GetMapping("/units")
    public List<EntityModel<Unit>> getAllUnits() {
        return Arrays.stream(Unit.values())
                .sorted(Comparator.comparing(Unit::getUnitType))
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
}
