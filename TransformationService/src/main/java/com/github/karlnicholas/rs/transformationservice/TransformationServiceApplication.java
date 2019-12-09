package com.github.karlnicholas.rs.transformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.github.karlnicholas.rs.serviceclient.AccountWebClient;

@SpringBootApplication(scanBasePackages = {"com.github.karlnicholas.rs"})
@EnableWebFlux
public class TransformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransformationServiceApplication.class, args);
	}

	@Bean
	AccountWebClient getAccountWebClient() {
		return new AccountWebClient();
	}
}
