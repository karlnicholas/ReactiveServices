package com.github.karlnicholas.rs.transactionservice.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.karlnicholas.rs.transactionservice.entity.TransactionEntity;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author Oliver Drotbohm
 */
@Component
@RequiredArgsConstructor
public class TransactionTransactionalService {

	private final @NonNull TransactionRepository repository;

	/**
	 * Saves the given {@link Customer} unless its firstname is "Dave".
	 *
	 * @param account must not be {@literal null}.
	 * @return
	 */
	@Transactional
	public Mono<TransactionEntity> save(TransactionEntity account) {

		return repository.save(account).map(it -> {

			if (it.getType().equals("REVERSAL")) {
				throw new IllegalStateException();
			} else {
				return it;
			}
		});
	}
}