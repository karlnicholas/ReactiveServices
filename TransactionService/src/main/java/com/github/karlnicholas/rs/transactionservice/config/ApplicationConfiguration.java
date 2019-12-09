package com.github.karlnicholas.rs.transactionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class ApplicationConfiguration extends AbstractR2dbcConfiguration {

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("sql/db-schema.sql")));
		populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("sql/test-data1.sql")));
		initializer.setDatabasePopulator(populator);

		return initializer;
	}

	@Override
	@Bean
	public ConnectionFactory connectionFactory() {
		return ConnectionFactories.get("r2dbc:h2:mem:///transactions?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
	}
}