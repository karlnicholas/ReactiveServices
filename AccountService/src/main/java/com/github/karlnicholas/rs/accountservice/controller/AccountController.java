package com.github.karlnicholas.rs.accountservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;
import com.github.karlnicholas.rs.accountservice.repository.AccountEntityRepository;
import com.github.karlnicholas.rs.model.account.AccountDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	@Autowired
	private AccountEntityRepository repo;
	@GetMapping("/{id}")
	Mono<AccountEntity> getAccountById(@PathVariable UUID id) {
	    return Mono.just(AccountEntity.builder()
	    		.firstname("Karl")
	    		.lastname("Nicholas")
	    		.id(id)
	    		.build());
	}
	
	@GetMapping("/name/{firstname}/{lastname}")
	Mono<AccountEntity> getAccountByAccount(@PathVariable String firstname, @PathVariable String lastname) {
	    return Mono.just(AccountEntity.builder()
	    		.firstname(firstname)
	    		.lastname(lastname)
	    		.id(UUID.randomUUID())
	    		.build());
	}

	@GetMapping("/idbyname/{firstname}/{lastname}")
	Mono<UUID> getAccountIdByAccount(@PathVariable String firstname, @PathVariable String lastname) {
	    return Mono.just(UUID.randomUUID());
	}

	@GetMapping("/count")
	Mono<Long> getRecordCount() {
	    return Mono.just(100000L);
	}

	@PostMapping("/createaccount")
	Mono<UUID> createAccount(@RequestBody AccountDto account) {
	    return Mono.just(UUID.randomUUID());
	}
    @MessageMapping("createaccounts")
    public Flux<UUID> createAccounts(Flux<AccountDto> accounts) {
	    return accounts.map(a->{
	    	return repo.save(AccountEntity.builder()
					.id(UUID.randomUUID())
					.firstname(a.getFirstname())
					.lastname(a.getLastname())
					.build())
    			.getId();
	    });
    }
}