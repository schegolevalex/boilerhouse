package com.schegolevalex.unit_converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.schegolevalex.unit_converter", "com.schegolevalex.unit_library"},
        exclude = {DataSourceAutoConfiguration.class})
public class UnitConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitConverterApplication.class, args);
    }
}
