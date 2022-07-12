package com.schegolevalex.boilerhouse.unit_converter.utils;

import com.schegolevalex.boilerhouse.unit_converter.entities.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomEnumConverterFactory /*implements ConverterFactory<String, Enum<?>>*/ {
//    @Autowired
//    @Qualifier("distance")
//    List<Enum> enumList;
//
//    @Override
//    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
//        return WithId.class.isAssignableFrom(targetType)
//                ? new EnumWithIdConverter(targetType)
//                : new StandardEnumConverter(targetType);
//    }
}
