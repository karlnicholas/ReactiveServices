package com.github.karlnicholas.rs.serviceclient;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.rs.model.account.Transaction;

import reactor.core.publisher.Mono;

@Component
public class TransactionWebClient {
	private WebClient webclient;

	public TransactionWebClient() {		
		webclient = WebClient.create("http://localhost:8110/transaction");
	}
	public Mono<UUID> createTransaction(Transaction transaction) {
		return webclient.post()
		     .uri("/createtransaction")
		     .contentType(MediaType.APPLICATION_JSON)
		     .bodyValue(transaction)
		     .retrieve()
		     .bodyToMono(UUID.class);
	}
}
