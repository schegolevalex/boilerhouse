package unit_converter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.Unit;
import unit_converter.measure_converters.ConverterProcessor;

import java.math.BigDecimal;

@RestController
public class ConverterController {

    private final ConverterProcessor converterProcessor;

    @Autowired
    public ConverterController(ConverterProcessor converterProcessor) {
        this.converterProcessor = converterProcessor;
    }

    @GetMapping("/convert")
    public Measure convert(@RequestParam("value") BigDecimal value,
                           @RequestParam("from") Unit unitFrom,
                           @RequestParam("to") Unit unitTo) {

        return converterProcessor.getConvertedResult(value, unitFrom, unitTo);
    }

    @GetMapping("/convert_to_primary")
    public Measure convertToPrimary(@RequestParam("value") BigDecimal value,
                                    @RequestParam("from") Unit unitFrom) {
        return converterProcessor.getConvertedResult(value, unitFrom);
    }
}
