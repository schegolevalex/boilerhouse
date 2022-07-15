package com.schegolevalex.boilerhouse.unit_converter.entities.checkers;

import com.schegolevalex.boilerhouse.unit_converter.entities.measures.Measure;

public interface MeasureChecker {

    default public boolean check(Measure measure) {
        return true;
    }
}
