package com.github.karlnicholas.rs.transformationservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.rs.model.account.AccountDto;
import com.github.karlnicholas.rs.model.account.AccountTransaction;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransformationController {
	@NonNull private WebClient webClient;
	@PostMapping("/createaccount")
	Mono<AccountDto> createAccount(@RequestBody Mono<AccountDto> account) {
		return webClient
				.post()
				.uri("/account/createaccount")
				.body(BodyInserters.fromProducer(account, AccountDto.class))
				.retrieve()
				.bodyToMono(AccountDto.class);
	}
	
	@PostMapping("/createaccounts")
	Flux<AccountDto> createAccount(@RequestBody Flux<AccountDto> accounts) {
		return webClient
				.post()
				.uri("/account/createaccounts")
				.body(BodyInserters.fromProducer(accounts, AccountDto.class))
				.retrieve()
				.bodyToFlux(AccountDto.class);
	}

	@PostMapping("/createtransaction")
	Mono<UUID> createTransaction(@RequestBody AccountTransaction accountTransaction) {
		return Mono.just(UUID.randomUUID());
	}
}