package com.schegolevalex.boilerhouse.pid.controllers.utils;

import com.schegolevalex.boilerhouse.unit_library.models.units.UnitType;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUnitTypeConverter implements Converter<String, UnitType> {
    @Override
    public UnitType convert(@NotNull String name) {
        return UnitType.valueOfFullName(name);
    }
}
