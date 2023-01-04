package com.schegolevalex.boilerhouse.pid.controllers.utils;

import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class StringToUnitConverter implements Converter<String, Unit> {
    @Override
    public Unit convert(String name) {
        return Unit.valueOfFullName(name);
    }
}
