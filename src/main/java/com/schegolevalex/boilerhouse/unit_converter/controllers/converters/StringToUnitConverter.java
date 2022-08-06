package com.schegolevalex.boilerhouse.unit_converter.controllers.converters;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import com.schegolevalex.boilerhouse.unit_converter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class StringToUnitConverter implements Converter<String, Unit> {
    private final UnitRepository unitRepository;

    @Autowired
    public StringToUnitConverter(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit convert(String name) {
        return unitRepository.getByFullName(name); //todo выбросить исключение
    }
}