package com.schegolevalex.unit_converter.controllers.converters;

import com.schegolevalex.unit_library.exceptions.IllegalUnitException;
import com.schegolevalex.unit_library.services.UnitService;
import com.schegolevalex.unit_library.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringToUnitConverter implements Converter<String, Unit> {
    private final UnitService unitService;

    @Autowired
    public StringToUnitConverter(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public Unit convert(String name) {
        Unit unit = unitService.getByFullName(name.toUpperCase(Locale.ROOT));
        if (unit == null) {
            throw new IllegalUnitException("No such unit \"" + name + "\" in database.");
        }
        return unit;
    }
}
