package com.github.karlnicholas.rs.datagenerator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.datagenerator.DataGenerator;
import com.github.karlnicholas.rs.serviceclient.TransformationWebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/datagenerator")
public class DataGeneratorController {

	@Autowired	
	private TransformationWebClient transformationWebClient;

	@GetMapping("/trigger")
	public Mono<Long> createAccountsWeb() throws IOException {
		return DataGenerator.generateAccounts()
		.flatMap(transformationWebClient::createAccount)
		.count();
	}

	@GetMapping("/triggertrans")
	public Mono<Long> createTransactionsWeb() throws IOException {
		return DataGenerator.generateTransactions()
		.flatMap(transformationWebClient::createTransaction)
		.count();
	}

}