package com.schegolevalex.boilerhouse.unit_converter.controllers;

import com.schegolevalex.boilerhouse.unit_converter.converters.MeasureConverter;
import com.schegolevalex.boilerhouse.unit_converter.entities.measure.Measure;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.DistanceMetric;
import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConverterController {

    final Map <Enum, Double> enumsMap;

    public ConverterController(Map<Enum, Double> enumsMap) {
        this.enumsMap = enumsMap;
    }

    @GetMapping("/")
    public Measure onReceive(@RequestParam("type") String type,
                             @RequestParam("value") double value,
                             @RequestParam("from") String from,
                             @RequestParam("to") String to) {

//        Unit unitFrom = Unit.getUnit(from);
//        Unit unitTo = Unit.getUnit(to);
//        Measure inputMeasure = new Measure(value, unitFrom);



//        return MeasureConverter.convert(inputMeasure, unitTo);
    }
}
