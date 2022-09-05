package com.schegolevalex.unit_converter.controllers.converters;

import com.schegolevalex.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import com.schegolevalex.unit_converter.exceptions.IllegalUnitException;
import com.schegolevalex.unit_converter.repositories.UnitRepository;

import java.util.Locale;

@Service
public class StringToUnitConverter implements Converter<String, Unit> {
    private final UnitRepository unitRepository;

    @Autowired
    public StringToUnitConverter(UnitRepository unitRepository) {
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
