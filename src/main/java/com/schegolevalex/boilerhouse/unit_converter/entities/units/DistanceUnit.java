package com.schegolevalex.boilerhouse.unit_converter.measure_units;

public enum DistanceUnit implements Unit {
    METER(1),
    KILOMETER(1000)
    //todo
    ;

    private double coefficient;

    private DistanceUnit(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }
}
