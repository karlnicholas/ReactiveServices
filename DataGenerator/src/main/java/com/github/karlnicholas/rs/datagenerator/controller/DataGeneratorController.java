package com.github.karlnicholas.rs.datagenerator.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.datagenerator.DataGenerator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/datagenerator")
@RequiredArgsConstructor
public class DataGeneratorController {
    private final @NonNull RSocketRequester rSocketRequester;
    
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
	public Mono<Long> createAccountsWeb() throws IOException {
        return rSocketRequester
                .route("createaccounts")
                .data(DataGenerator.generateAccounts())
                .retrieveFlux(UUID.class)
                .count();
	}

    @GetMapping("/triggertrans")
	public Mono<Long> createTransactionsWeb() throws IOException {
		return DataGenerator.generateTransactions()
//		.flatMap(transformationWebClient::createTransaction)
		.count();
	}

}