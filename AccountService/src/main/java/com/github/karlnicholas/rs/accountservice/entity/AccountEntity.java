package com.github.karlnicholas.rs.accountservice.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class AccountEntity {
	@Id private UUID id;
	private String firstname, lastname;
}
