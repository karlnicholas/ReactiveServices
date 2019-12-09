package com.github.karlnicholas.rs.datagenerator.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.datagenerator.DataGenerator;
import com.github.karlnicholas.rs.model.account.Account;
import com.github.karlnicholas.rs.serviceclient.TransformationWebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/datagenerator")
public class DataGeneratorController {

	@Autowired
	private RSocketRequester rsocketRequester;
	@Autowired	
	private TransformationWebClient transformationWebClient;

	@GetMapping("/trigger")
	public Mono<Long> createAccounts() throws IOException {

		return rsocketRequester
				.route("transformationaccounts")
				.data(DataGenerator.generateAccounts(), Account.class)
				.retrieveFlux(UUID.class)
				.count();
	}

	@GetMapping("/triggerweb")
	public Mono<Long> createAccountsWeb() throws IOException {
		return DataGenerator.generateAccounts()
		.flatMap(transformationWebClient::createAccount)
		.count();
	}

}