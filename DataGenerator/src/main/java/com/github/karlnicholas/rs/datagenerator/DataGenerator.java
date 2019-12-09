package com.github.karlnicholas.rs.datagenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.BaseStream;

import org.springframework.core.io.ClassPathResource;

import com.github.karlnicholas.rs.model.account.Account;
import com.github.karlnicholas.rs.model.account.AccountTransaction;
import com.github.karlnicholas.rs.model.account.Transaction;
import com.github.karlnicholas.rs.model.account.TransactionType;

import reactor.core.publisher.Flux;

public class DataGenerator {
	public static Flux<Account> generateAccounts() {
		return fluxAccounts();
	}

	public static Flux<AccountTransaction> generateTransactions() {
		return fluxAccounts()
			.map(a->AccountTransaction.builder()
				.account(a)
				.transaction(
						Transaction.builder()
						.amount(new BigDecimal("10.00"))
						.trDate(LocalDate.parse("2019-12-08"))
						.type(TransactionType.DEBIT)
						.build()
				)
				.build()
			);
	}

	private static Flux<Account> fluxAccounts() {
		return Flux.using(() -> 
			new BufferedReader(new InputStreamReader(new ClassPathResource("data/ExportCSV.csv").getInputStream()))
				.lines()
				.map(s->{
					String[] sa = s.split(" ");
					return Account.builder()
						.firstname(sa[0])
						.lastname(sa[1])
						.build();
				}),
				Flux::fromStream,
				BaseStream::close
		);
	}
}
