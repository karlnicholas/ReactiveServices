package com.github.karlnicholas.rs.transformationservice.controller;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.model.account.Account;
import com.github.karlnicholas.rs.model.account.AccountTransaction;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transformation")
@RequiredArgsConstructor
@Controller
public class TransformationController {
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
    public Mono<UUID> createAccount(Account account) {
		return Mono.just(UUID.randomUUID());
    }
    @MessageMapping("createaccounts")
    public Flux<UUID> createAccounts(Flux<Account> accounts) {
		return accounts.map(a->UUID.randomUUID());
    }
}