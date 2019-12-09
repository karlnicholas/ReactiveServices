package com.github.karlnicholas.rs.accountservice.rsocket;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;
import com.github.karlnicholas.rs.accountservice.repository.AccountRepository;
import com.github.karlnicholas.rs.model.account.Account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class AccountPublisher {

//	private final RSocketRequester requester;
	private final @NonNull AccountRepository accountRepository;

	@MessageMapping("accountaccounts")
	public Flux<UUID> createAccounts(Flux<Account> accounts) {
		return accounts.flatMap(account->accountRepository.save(AccountEntity.builder()
				.firstname(account.getFirstname())
				.lastname(account.getLastname())
				.id(UUID.randomUUID())
				.newFlag(true)
				.build())
			.map(AccountEntity::getId));
                
	}

}
