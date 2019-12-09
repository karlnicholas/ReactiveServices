package com.github.karlnicholas.rs.transformationservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.model.account.Account;
import com.github.karlnicholas.rs.model.account.AccountTransaction;
import com.github.karlnicholas.rs.model.account.Transaction;
import com.github.karlnicholas.rs.serviceclient.AccountWebClient;
import com.github.karlnicholas.rs.serviceclient.TransactionWebClient;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transformation")
@RequiredArgsConstructor
public class TransformationController {
	
	private final @NonNull AccountWebClient accountWebClient; 
	private final @NonNull TransactionWebClient transactionWebClient; 
	
	@PostMapping("/createaccount")
	Mono<UUID> createAccount(@RequestBody Account account) {
		return accountWebClient.createAccount(account);
	}
	
	@PostMapping("/createtransaction")
	Mono<UUID> createTransaction(@RequestBody AccountTransaction accountTransaction) {
		return accountWebClient.accountIdByName(
			accountTransaction.getAccount().getFirstname(), 
			accountTransaction.getAccount().getLastname() 
		)
		.flatMap(accountId->{
			Transaction transaction = accountTransaction.getTransaction();
			transaction.setAccountId(accountId);
			return transactionWebClient.createTransaction(transaction);
		});            
	}
}