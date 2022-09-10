package com.schegolevalex.heat_engineering_calculations.entities;

import com.schegolevalex.unit_library.entities.units.UnitType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
