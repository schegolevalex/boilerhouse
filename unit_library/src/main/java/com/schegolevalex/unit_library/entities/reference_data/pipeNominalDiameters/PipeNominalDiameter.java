package com.schegolevalex.unit_library.entities.reference_data.pipeNominalDiameters;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PipeNominalDiameterSerializer.class)
@JsonDeserialize(using = PipeNominalDiameterDeserializer.class)
public enum PipeNominalDiameter {
    DN8(8),
    DN10(10),
    DN15(15),
    DN20(20),
    DN25(25),
    DN32(32),
    DN40(40),
    DN50(50),
    DN65(65),
    DN80(80),
    DN100(100),
    DN125(125),
    DN150(150),
    DN200(200),
    DN250(250),
    DN300(300),
    DN350(350),
    DN400(400),
    DN450(450),
    DN500(500),
    DN600(600),
    DN700(700),
    DN800(800),
    DN900(900),
    DN1000(1000);

    private double diameter;

    PipeNominalDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }

    public static PipeNominalDiameter valueOfDiameter(double diameter) {
        for (PipeNominalDiameter pipeNominalDiameter : PipeNominalDiameter.values()) {
            if (pipeNominalDiameter.getDiameter() == diameter)
                return pipeNominalDiameter;
        }
        return null;
    }
}
