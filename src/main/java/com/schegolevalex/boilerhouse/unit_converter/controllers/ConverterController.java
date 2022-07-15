package com.schegolevalex.boilerhouse.unit_converter.controllers;

import com.schegolevalex.boilerhouse.unit_converter.measure_converters.ConverterProcessor;
import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@RestController
public class ConverterController {

    private final ConverterProcessor converterProcessor;

    @Autowired
    public ConverterController(ConverterProcessor converterProcessor) {
        this.converterProcessor = converterProcessor;
    }

    @GetMapping("/")
    public Measure onReceive(@RequestParam("value") BigDecimal value,
                             @RequestParam("from") Unit unitFrom,
                             @RequestParam("to") Unit unitTo) {

        Measure inputMeasure = new Measure(value, unitFrom);
        return converterProcessor.getConvertedResult(inputMeasure, unitTo);
    }
}
