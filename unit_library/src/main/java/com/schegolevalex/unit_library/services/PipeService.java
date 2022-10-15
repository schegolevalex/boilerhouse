package com.schegolevalex.unit_library.services;

import com.schegolevalex.unit_library.models.measures.MeasureFactory;
import com.schegolevalex.unit_library.models.reference_data.Pipe;
import com.schegolevalex.unit_library.models.reference_data.PipeMaterial;
import com.schegolevalex.unit_library.models.reference_data.PipeNominalDiameter;
import com.schegolevalex.unit_library.models.units.Unit;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PipeService {
    final MeasureFactory measureFactory;
    final String FILE = "unit_library/src/main/resources/Трубы ГОСТ 10704-91.csv";
    final Map<String, List<Pipe>> pipesByStandardMap = new HashMap<>();

    @Autowired
    public PipeService(MeasureFactory measureFactory) {
        this.measureFactory = measureFactory;
    }

    @PostConstruct
    public void init() {
        List<Pipe> pipes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                pipes.add(new Pipe(PipeMaterial.valueOf(split[0]),
                        PipeNominalDiameter.valueOfDiameter(Integer.parseInt(split[1])),
                        new BigDecimal(split[2]),
                        new BigDecimal(split[3]),
                        new BigDecimal(split[4]),
                        split[5],
                        measureFactory.createMeasure(new BigDecimal(split[6]), Unit.KILOGRAM)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pipesByStandardMap.put("ГОСТ 10704-91", pipes);
    }

}
