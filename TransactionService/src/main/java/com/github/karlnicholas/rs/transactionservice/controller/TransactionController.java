package com.github.karlnicholas.rs.transactionservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.model.account.Transaction;
import com.github.karlnicholas.rs.transactionservice.entity.TransactionEntity;
import com.github.karlnicholas.rs.transactionservice.repository.TransactionRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
	
	private final @NonNull TransactionRepository repository; 
	@GetMapping("/{id}")
	Mono<TransactionEntity> getTransactionById(@PathVariable UUID id) {
	    return repository.findById(id);
	}

	@GetMapping("/count")
	Mono<Long> getRecordCount() {
	    return repository.count();
	}
	
	@PostMapping("/createtransaction")
	Mono<UUID> createTransaction(@RequestBody Transaction transaction) {
		return repository.save(
			TransactionEntity.builder()
			.id(UUID.randomUUID())
			.newFlag(true)
			.amount(transaction.getAmount())
			.trDate(transaction.getTrDate())
			.type(transaction.getType())
			.build()
			)
		.map(TransactionEntity::getId);
	}
	
}