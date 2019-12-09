package com.github.karlnicholas.rs.transactionservice.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

import com.github.karlnicholas.rs.transactionservice.entity.TransactionEntity;

@Component
public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, UUID> {
}