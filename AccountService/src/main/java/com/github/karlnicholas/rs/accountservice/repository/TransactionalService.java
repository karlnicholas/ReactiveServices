package com.github.karlnicholas.rs.accountservice.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.karlnicholas.rs.accountservice.entity.AccountEntity;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author Oliver Drotbohm
 */
@Component
@RequiredArgsConstructor
public class TransactionalService {

	private final @NonNull AccountRepository repository;

	/**
	 * Saves the given {@link Customer} unless its firstname is "Dave".
	 *
	 * @param account must not be {@literal null}.
	 * @return
	 */
	@Transactional
	public Mono<AccountEntity> save(AccountEntity account) {

		return repository.save(account).map(it -> {

			if (it.getFirstname().equals("Denny")) {
				throw new IllegalStateException();
			} else {
				return it;
			}
		});
	}
}