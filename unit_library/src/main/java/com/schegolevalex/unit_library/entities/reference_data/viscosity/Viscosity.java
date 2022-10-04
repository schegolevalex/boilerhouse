package com.schegolevalex.unit_library.entities.reference_data.viscosity;

import com.schegolevalex.unit_library.entities.measures.Measure;
import com.schegolevalex.unit_library.entities.reference_data.pipe_materials.PipeMaterial;
import com.schegolevalex.unit_library.entities.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Viscosity {
    private static Map<Measure, Measure> viscosityMap = new HashMap<>();

    static {
        viscosityMap.put(new Measure(0, Unit.DEGREE_CELSIUS), new Measure(1.787E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(5, Unit.DEGREE_CELSIUS), new Measure(1.522E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(10, Unit.DEGREE_CELSIUS), new Measure(1.314E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(20, Unit.DEGREE_CELSIUS), new Measure(1.01E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(30, Unit.DEGREE_CELSIUS), new Measure(0.814E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(40, Unit.DEGREE_CELSIUS), new Measure(0.667E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(50, Unit.DEGREE_CELSIUS), new Measure(0.559E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(60, Unit.DEGREE_CELSIUS), new Measure(0.476E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(70, Unit.DEGREE_CELSIUS), new Measure(0.411E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(80, Unit.DEGREE_CELSIUS), new Measure(0.36E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(90, Unit.DEGREE_CELSIUS), new Measure(0.318E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(100, Unit.DEGREE_CELSIUS), new Measure(0.283E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(110, Unit.DEGREE_CELSIUS), new Measure(0.2681E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(120, Unit.DEGREE_CELSIUS), new Measure(0.2461E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(130, Unit.DEGREE_CELSIUS), new Measure(0.2275E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(140, Unit.DEGREE_CELSIUS), new Measure(0.2118E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(150, Unit.DEGREE_CELSIUS), new Measure(0.1984E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(160, Unit.DEGREE_CELSIUS), new Measure(1.522E-6, Unit.SQUARE_METER_PER_SECOND));
        viscosityMap.put(new Measure(170, Unit.DEGREE_CELSIUS), new Measure(1.522E-6, Unit.SQUARE_METER_PER_SECOND));
    }

    public static Measure getViscosity(PipeMaterial pipeMaterial) {
        return viscosityMap.get(pipeMaterial);
    }

    public static Map<Measure, Measure> getViscosityMap() {
        return viscosityMap;
    }
}
