package com.schegolevalex.boilerhouse.unit_converter.controllers;

import com.schegolevalex.boilerhouse.unit_converter.ConverterProcessor;
import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class ConverterController {

    final ConverterProcessor converterProcessor;
    final UnitRepository unitRepository;

    @Autowired
    public ConverterController(ConverterProcessor converterProcessor,
                               UnitRepository unitRepository) {
        this.converterProcessor = converterProcessor;
        this.unitRepository = unitRepository;
    }

    @GetMapping("/")
    public Measure onReceive(@RequestParam("value") BigDecimal value,
                             @RequestParam("from") String from,
                             @RequestParam("to") String to) {

        Unit unitFrom = unitRepository.getByFullName(from);
        Unit unitTo = unitRepository.getByFullName(to);
        Measure inputMeasure = new Measure(value, unitFrom);
        return converterProcessor.getConvertedResult(inputMeasure, unitTo);
    }
}
