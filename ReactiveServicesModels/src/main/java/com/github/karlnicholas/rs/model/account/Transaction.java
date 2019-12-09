package com.github.karlnicholas.rs.model.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private UUID accountId;
	private BigDecimal amount;
	private TransactionType type;
	private LocalDate trDate;
}
