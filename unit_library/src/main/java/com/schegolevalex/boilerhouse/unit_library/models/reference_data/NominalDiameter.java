package com.schegolevalex.boilerhouse.unit_library.models.reference_data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.schegolevalex.boilerhouse.unit_library.models.measures.Measure;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import com.schegolevalex.boilerhouse.unit_library.exceptions.IllegalValueException;
import com.schegolevalex.boilerhouse.unit_library.config.serdeser.PipeNominalDiameterDeserializer;
import com.schegolevalex.boilerhouse.unit_library.config.serdeser.PipeNominalDiameterSerializer;

import java.math.BigDecimal;

@JsonSerialize(using = PipeNominalDiameterSerializer.class)
@JsonDeserialize(using = PipeNominalDiameterDeserializer.class)
public enum NominalDiameter {
    DN8(new Measure(8, Unit.MILLIMETER)),
    DN10(new Measure(10, Unit.MILLIMETER)),
    DN12(new Measure(12, Unit.MILLIMETER)),
    DN15(new Measure(15, Unit.MILLIMETER)),
    DN20(new Measure(20, Unit.MILLIMETER)),
    DN25(new Measure(25, Unit.MILLIMETER)),
    DN32(new Measure(32, Unit.MILLIMETER)),
    DN40(new Measure(40, Unit.MILLIMETER)),
    DN50(new Measure(50, Unit.MILLIMETER)),
    DN65(new Measure(65, Unit.MILLIMETER)),
    DN80(new Measure(80, Unit.MILLIMETER)),
    DN100(new Measure(100, Unit.MILLIMETER)),
    DN125(new Measure(125, Unit.MILLIMETER)),
    DN150(new Measure(150, Unit.MILLIMETER)),
    DN160(new Measure(160, Unit.MILLIMETER)),
    DN175(new Measure(175, Unit.MILLIMETER)),
    DN200(new Measure(200, Unit.MILLIMETER)),
    DN250(new Measure(250, Unit.MILLIMETER)),
    DN300(new Measure(300, Unit.MILLIMETER)),
    DN350(new Measure(350, Unit.MILLIMETER)),
    DN400(new Measure(400, Unit.MILLIMETER)),
    DN450(new Measure(450, Unit.MILLIMETER)),
    DN500(new Measure(500, Unit.MILLIMETER)),
    DN600(new Measure(600, Unit.MILLIMETER)),
    DN700(new Measure(700, Unit.MILLIMETER)),
    DN800(new Measure(800, Unit.MILLIMETER)),
    DN900(new Measure(900, Unit.MILLIMETER)),
    DN1000(new Measure(1000, Unit.MILLIMETER)),
    DN1200(new Measure(1200, Unit.MILLIMETER)),
    DN1400(new Measure(1400, Unit.MILLIMETER))

    ;

    private Measure diameter;

    NominalDiameter(Measure diameter) {
        this.diameter = diameter;
    }

    public Measure getDiameter() {
        return diameter;
    }

    public static NominalDiameter valueOfDiameter(int diameter) {
        for (NominalDiameter nominalDiameter : NominalDiameter.values()) {
            if (nominalDiameter.getDiameter().getValue().compareTo(BigDecimal.valueOf(diameter)) == 0)
                return nominalDiameter;
        }
        throw new IllegalValueException("No such pipe nominal diameter");
    }
}
