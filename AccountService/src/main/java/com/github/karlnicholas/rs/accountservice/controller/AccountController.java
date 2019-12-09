package com.github.karlnicholas.rs.accountservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;
import com.github.karlnicholas.rs.accountservice.repository.AccountRepository;
import com.github.karlnicholas.rs.accountservice.repository.TransactionalService;
import com.github.karlnicholas.rs.model.account.Account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	private final @NonNull AccountRepository accountRepository;
	private final @NonNull TransactionalService transactionalService;
	@GetMapping("/{id}")
	Mono<AccountEntity> getAccountById(@PathVariable UUID id) {
	    return accountRepository.findById(id);
	}
	
	@GetMapping("/name/{firstname}/{lastname}")
	Mono<AccountEntity> getAccountByAccount(@PathVariable String firstname, @PathVariable String lastname) {
	    return accountRepository.findByName(firstname, lastname);
	}

	@GetMapping("/idbyname/{firstname}/{lastname}")
	Mono<UUID> getAccountIdByAccount(@PathVariable String firstname, @PathVariable String lastname) {
	    return accountRepository.findByName(firstname, lastname).map(AccountEntity::getId);
	}

	@GetMapping("/count")
	Mono<Long> getRecordCount() {
	    return accountRepository.count();
	}

	@PostMapping("/createaccount")
	Mono<UUID> createAccount(@RequestBody Account account) {
		return accountRepository.save(AccountEntity.builder()
				.firstname(account.getFirstname())
				.lastname(account.getLastname())
				.id(UUID.randomUUID())
				.newFlag(true)
				.build())
			.map(AccountEntity::getId);
	}
}