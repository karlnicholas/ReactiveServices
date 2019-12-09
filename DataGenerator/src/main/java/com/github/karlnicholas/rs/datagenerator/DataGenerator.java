package com.github.karlnicholas.rs.datagenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.BaseStream;

import org.springframework.core.io.ClassPathResource;

import com.github.karlnicholas.rs.model.account.Account;

import reactor.core.publisher.Flux;

public class DataGenerator {
	public static Flux<Account> generateAccounts() {
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
