package com.github.karlnicholas.rs.datagenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = {"com.github.karlnicholas.rs"})
@EnableWebFlux
public class DataGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataGeneratorApplication.class, args);
	}

}
