package com.github.karlnicholas.rs.transactionservice.entity;

import java.util.UUID;

import com.github.karlnicholas.rs.model.account.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@EqualsAndHashCode(callSuper=false)
public class TransactionEntity extends Transaction {
	private UUID id;
}
