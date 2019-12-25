package com.github.karlnicholas.rs.transactionservice.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.rs.model.account.Transaction;
import com.github.karlnicholas.rs.model.account.TransactionType;
import com.github.karlnicholas.rs.transactionservice.entity.TransactionEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
	
	@GetMapping("/{id}")
	Mono<TransactionEntity> getTransactionById(@PathVariable UUID id) {
	    return Mono.just(TransactionEntity.builder()
	    		.id(id)
	    		.amount(BigDecimal.TEN)
	    		.trDate(LocalDate.of(2019, 12, 25))
	    		.type(TransactionType.CREDIT)
	    		.accountId(UUID.randomUUID())
	    		.build());
	}

	@GetMapping("/count")
	Mono<Long> getRecordCount() {
	    return Mono.just(100000L);
	}
	
	@PostMapping("/createtransaction")
	Mono<UUID> createTransaction(@RequestBody Transaction transaction) {
	    return Mono.just(UUID.randomUUID());
	}
	
}