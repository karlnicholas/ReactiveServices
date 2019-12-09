package com.github.karlnicholas.rs.transformationservice.rsocket;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;

import com.github.karlnicholas.rs.model.account.Account;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import reactor.core.publisher.Flux;

@Controller
@AllArgsConstructor
public class TransformationPublisher {

	private @NonNull final RSocketRequester rsocketMono;

	@MessageMapping("transformationaccounts")
	public Flux<UUID> createAccounts(Flux<Account> accounts) {

			return rsocketMono
					.route("accountaccounts")
					.data(accounts, Account.class)
					.retrieveFlux(UUID.class);
	}

}
