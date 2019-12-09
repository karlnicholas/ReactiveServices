package com.github.karlnicholas.rs.accountservice.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.karlnicholas.rs.model.account.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@EqualsAndHashCode(callSuper=false)
public class AccountEntity extends Account implements Persistable<UUID> {
	@Id 
	private UUID id;
	@JsonIgnore
	@Transient
	@Builder.Default
	private boolean newFlag = false;

	@Override
	@JsonIgnore
	public boolean isNew() {
		return newFlag; // should be backed by code that is able to figure out whether the entity is new.
	}
}
