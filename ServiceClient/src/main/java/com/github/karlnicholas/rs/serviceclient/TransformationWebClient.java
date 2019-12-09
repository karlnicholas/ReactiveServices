package com.github.karlnicholas.rs.serviceclient;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.rs.model.account.Account;

import reactor.core.publisher.Mono;


@Component
public class TransformationWebClient {
	private WebClient webclient;

	public TransformationWebClient() {		
		webclient = WebClient.create("http://localhost:8090/transformation");
	}
	public Mono<UUID> createAccount(Account account) {
		return webclient.post()
		     .uri("/createaccount")
		     .contentType(MediaType.APPLICATION_JSON)
		     .bodyValue(account)
		     .retrieve()
		     .bodyToMono(UUID.class);
	}
}
