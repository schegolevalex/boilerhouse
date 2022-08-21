package unit_converter.controllers;

import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.Unit;
import unit_converter.measure_converters.ConverterProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        return converterProcessor.getConvertedResult(value, unitFrom, unitTo);
    }
}
