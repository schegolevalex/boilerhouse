package com.schegolevalex.unit_converter.controllers.converters;

import com.schegolevalex.library.entities.units.Unit;
import com.schegolevalex.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.unit_converter.repositories.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StringToUnitConverter implements Converter<String, Unit> {
    private final UnitService unitRepository;

    @Autowired
    public StringToUnitConverter(UnitService unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit convert(String name) {
        Unit unit = unitRepository.getByFullName(name.toUpperCase(Locale.ROOT));
        if (unit == null) {
            throw new IllegalUnitException("No such unit \"" + name + "\" in database.");
        }
        return unit;
    }
}
