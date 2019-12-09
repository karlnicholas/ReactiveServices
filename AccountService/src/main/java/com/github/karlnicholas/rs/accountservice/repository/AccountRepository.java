package com.github.karlnicholas.rs.accountservice.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;

import reactor.core.publisher.Mono;

@Component
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, UUID> {
	@Query("select id, firstname, lastname from account_entity a where a.firstname = :firstname and a.lastname = :lastname FETCH FIRST 1 ROWS ONLY")
	Mono<AccountEntity> findByName(String firstname, String lastname);
	
}