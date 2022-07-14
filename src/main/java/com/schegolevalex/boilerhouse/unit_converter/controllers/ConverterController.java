package com.schegolevalex.boilerhouse.unit_converter.controllers;

import com.schegolevalex.boilerhouse.unit_converter.converters.ConverterProcessor;
import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.beans.Transient;
import java.math.BigDecimal;

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

    @PostConstruct
    private void addData() {
        unitRepository.save(new DistanceMetric("METER", "m", 1, true));
        unitRepository.save(new DistanceMetric("DEKAMETER", "dam", 1E1, false));
        unitRepository.save(new DistanceMetric("HECTOMETER", "hm", 1E2, false));
        unitRepository.save(new DistanceMetric("KILOMETER", "km", 1E3, false));
        unitRepository.save(new DistanceMetric("DECIMETER", "dm", 1E-1, false));
        unitRepository.save(new DistanceMetric("CENTIMETER", "cm", 1E-2, false));
        unitRepository.save(new DistanceMetric("MILLIMETER", "mm", 1E-3, false));
        unitRepository.save(new DistanceMetric("MICROMETER", "micron", 1E-6, false));
        unitRepository.save(new DistanceMetric("NANOMETER", "nm", 1E-9, false));
        unitRepository.save(new DistanceMetric("ANGSTROM", null, 1E-10, false));
    }
}
