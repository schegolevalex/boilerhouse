package com.schegolevalex.boilerhouse.heat_engineering_calculations.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import unit_converter.entities.units.UnitType;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ClientUnit {
    UnitType type;

    String fullName;

    String shortName;

    Map<String, String> urls;

    public ClientUnit(UnitType type, String fullName, String shortName, Map<String, String> urls) {
        this.type = type;
        this.fullName = fullName;
        this.shortName = shortName;
        this.urls = urls;
    }
}
