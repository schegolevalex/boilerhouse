package com.schegolevalex.unit_converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.schegolevalex.unit_converter", "com.schegolevalex.unit_library"})
@EnableJpaRepositories("com.schegolevalex.unit_library.repositories")
public class UnitConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitConverterApplication.class, args);
    }
}
