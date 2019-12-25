package com.github.karlnicholas.rs.accountservice.entity;

import java.util.UUID;

import com.github.karlnicholas.rs.model.account.Account;

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
public class AccountEntity extends Account {
	private UUID id;
}
