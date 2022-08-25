package com.schegolevalex.boilerhouse.heat_engineering_calculations.calculations;

import com.schegolevalex.boilerhouse.heat_engineering_calculations.clients.UnitConverterClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unit_converter.entities.measures.Measure;
import unit_converter.entities.units.FlowRateByMass;

import java.math.BigDecimal;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Calculation {

    final UnitConverterClient unitConverterClient;
    final BigDecimal HEAT_CAPACITY = BigDecimal.valueOf(1.0);

    @Autowired
    public Calculation(UnitConverterClient unitConverterClient) {
        this.unitConverterClient = unitConverterClient;
    }

    public FlowRateByMass getFlowRateByMass(Measure power, Measure temperatureLow, Measure temperatureHigh) {
//        Measure primaryPower = unitConverterClient.convert(power);
//        Measure primaryTemperatureLow = unitConverterClient.convert(temperatureLow);
//        Measure primaryTemperatureHigh = unitConverterClient.convert(temperatureHigh);


        return null;
    }


}
