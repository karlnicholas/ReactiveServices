package com.github.karlnicholas.rs.accountservice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;
import com.github.karlnicholas.rs.accountservice.repository.AccountEntityRepository;

@SpringBootApplication
@EntityScan({"com.github.karlnicholas.rs.model.account", "com.github.karlnicholas.rs.accountservice.entity"})
public class AccountServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Autowired
	private AccountEntityRepository repo;
	@Override
	public void run(String... args) throws Exception {
		AccountEntity entity = AccountEntity.builder()
				.id(UUID.randomUUID())
				.firstname("Karl")
				.lastname("Nicholas")
				.build();
		repo.save(entity);
		
	}

}
