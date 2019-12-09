package com.github.karlnicholas.rs.serviceclient;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.rs.model.account.Account;

import reactor.core.publisher.Mono;

@Component
public class AccountWebClient {
	private WebClient webclient;
	public AccountWebClient() {
		webclient = WebClient.create("http://localhost:8080/account");
	}
	public Mono<UUID> createAccount(Account account) {
		return webclient.post()
		     .uri("/createaccount")
		     .contentType(MediaType.APPLICATION_JSON)
		     .bodyValue(account)
		     .retrieve()
		     .bodyToMono(UUID.class);
	}
	public Mono<Account> lookupAccount(String firstname, String lastname  ) {
		return webclient.get()
		     .uri("/name/"+firstname+"/"+lastname)
		     .retrieve()
		     .bodyToMono(Account.class);
	}
	public Mono<UUID> accountIdByName(String firstname, String lastname  ) {
		return webclient.get()
		     .uri("/idbyname/"+firstname+"/"+lastname)
		     .retrieve()
		     .bodyToMono(UUID.class);
	}
}
