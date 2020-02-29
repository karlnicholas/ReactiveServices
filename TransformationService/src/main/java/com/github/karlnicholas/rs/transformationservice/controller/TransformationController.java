package com.github.karlnicholas.rs.transformationservice.controller;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.model.account.AccountDto;
import com.github.karlnicholas.rs.model.account.AccountTransaction;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class TransformationController {
	private final @NonNull RSocketRequester rSocketRequester;
/*	
	@PostMapping("/createaccount")
	Mono<UUID> createAccount(@RequestBody Account account) {
		return Mono.just(UUID.randomUUID());
	}
	
	@PostMapping("/createtransaction")
	Mono<UUID> createTransaction(@RequestBody AccountTransaction accountTransaction) {
		return Mono.just(UUID.randomUUID());
	}
*/	
    @MessageMapping("createaccount")
    public Mono<UUID> createAccount(AccountDto account) {
		return Mono.just(UUID.randomUUID());
    }
    @MessageMapping("createaccounts")
    public Flux<AccountDto> createAccounts(Flux<AccountDto> accounts) {
        return rSocketRequester
            .route("createaccounts")
            .data(accounts)
            .retrieveFlux(AccountDto.class);
    }
}