package unit_converter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.controllers.converters.UnitModelAssembler;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.Unit;
import unit_converter.exceptions.IllegalUnitException;
import unit_converter.measure_converters.ConverterProcessor;
import unit_converter.repositories.UnitRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ConverterController {

    private final ConverterProcessor converterProcessor;
    private final UnitRepository unitRepository;
    private final UnitModelAssembler assembler;

    @Autowired
    public ConverterController(ConverterProcessor converterProcessor,
                               UnitRepository unitRepository,
                               UnitModelAssembler assembler) {
        this.converterProcessor = converterProcessor;
        this.unitRepository = unitRepository;
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
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new IllegalUnitException("No such unit in database"));
        return assembler.toModel(unit);
    }

    @GetMapping("/units")
    public CollectionModel<EntityModel<Unit>> getAllUnits() {
        List<EntityModel<Unit>> units = unitRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(units,
                linkTo(methodOn(ConverterController.class).getAllUnits()).withSelfRel());
    }
}
