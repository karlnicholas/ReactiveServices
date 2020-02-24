package com.github.karlnicholas.rs.accountservice.entity;

import java.util.UUID;

import javax.persistence.*;

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
