package com.github.karlnicholas.rs.datagenerator.controller;


import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.rs.datagenerator.DataGenerator;
import com.github.karlnicholas.rs.model.account.AccountDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/datagenerator")
@RequiredArgsConstructor
public class DataGeneratorController {
    @NonNull private WebClient webClient;
 /*
	@GetMapping("/trigger")
	public Mono<Long> createAccountsWeb() throws IOException {
        return DataGenerator.generateAccounts()
    		   .map(account->{
    			   return rSocketRequester
    			   .route("createaccount")
                   .data(account)
                   .retrieveMono(UUID.class);
    		   }).count();
                
//		.flatMap(transformationWebClient::createAccount)
//		.count();
	}

*/
	@GetMapping("/trigger")
	public Flux<AccountDto> createAccountsWeb() throws IOException, URISyntaxException {
		return webClient
			.post()
			.uri("createaccounts")
			.body(BodyInserters.fromProducer(DataGenerator.generateAccounts(), AccountDto.class))
			.retrieve()
			.bodyToFlux(AccountDto.class);
	}

	@GetMapping("/triggerm")
	public Mono<AccountDto> createAccountWeb() throws IOException, URISyntaxException {
		return WebClient.create("http://localhost:8090/createaccount")
		.post()
		.body(BodyInserters.fromProducer(Mono.just(AccountDto.builder().firstname("Karl").lastname("Mniocholas").build()), AccountDto.class))
		.retrieve()
		.bodyToMono(AccountDto.class);
	}

	@GetMapping("/triggertrans")
	public Mono<Long> createTransactionsWeb() throws IOException {
		return DataGenerator.generateTransactions()
//		.flatMap(transformationWebClient::createTransaction)
		.count();
	}

}