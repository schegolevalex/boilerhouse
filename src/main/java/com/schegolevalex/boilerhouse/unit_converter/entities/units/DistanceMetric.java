package com.schegolevalex.boilerhouse.unit_converter.entities.units;

public enum DistanceMetric implements Unit {
    METER(1),
    DEKAMETER(10),
    HECTOMETER (100),
    KILOMETER(1000),
    DECIMETER(0.1),
    CENTIMETER(0.01),
    MILLIMETER(0.001),
    MICROMETER(0.000001),
    MICRON(0.000001),
    NANOMETER(0.000000001),
    ANGSTROM(0.0000000001),
    ;

    static UnitType type = UnitType.DISTANCE;
    private double coefficient;

    DistanceMetric(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }
}
