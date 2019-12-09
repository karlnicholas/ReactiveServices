package com.github.karlnicholas.rs.transformationservice.rsocket;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

@Configuration
public class AccountRSocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketStrategies strategies) {
    	return RSocketRequester.builder()
    			.rsocketStrategies(strategies)
    			.connectWebSocket(URI.create("ws://localhost:8080/rsocket"))
    			.block();
    }
}
