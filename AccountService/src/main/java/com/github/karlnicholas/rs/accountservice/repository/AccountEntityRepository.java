package com.github.karlnicholas.rs.accountservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;

public interface AccountEntityRepository extends PagingAndSortingRepository<AccountEntity, UUID> {

}
