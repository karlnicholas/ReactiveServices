package com.github.karlnicholas.rs.accountservice.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;
import com.github.karlnicholas.rs.accountservice.repository.AccountEntityRepository;
import com.github.karlnicholas.rs.model.account.AccountDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	@NonNull
	private AccountEntityRepository repo;
	@GetMapping("/{id}")
	Mono<Optional<AccountEntity>> getAccountById(@PathVariable UUID id) {
	    return Mono.just(repo.findById(id));
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
	Mono<AccountDto> createAccount(@RequestBody Mono<AccountDto> account) {
	    return account.map(a->{
			a.setId(
				repo.save(AccountEntity.builder().firstname(a.getFirstname()).lastname(a.getLastname()).id(UUID.randomUUID()).build()).getId()
			);
	    	return a;
	    });
	}
	@PostMapping("/createaccounts")
	Flux<AccountDto> createAccounts(@RequestBody Flux<AccountDto> accounts) {
	    return accounts.map(a->{
			a.setId(
				repo.save(AccountEntity.builder().firstname(a.getFirstname()).lastname(a.getLastname()).id(UUID.randomUUID()).build()).getId()
			);
	    	return a;
	    });
	}
}