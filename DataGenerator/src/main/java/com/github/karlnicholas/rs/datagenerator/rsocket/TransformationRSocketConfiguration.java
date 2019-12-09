package com.github.karlnicholas.rs.datagenerator.rsocket;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

@Configuration
public class TransformationRSocketConfiguration {
    @Bean
    public RSocketRequester rSocketRequester(RSocketStrategies strategies) {
    	return RSocketRequester.builder()
    			.rsocketStrategies(strategies)
    			.connectWebSocket(URI.create("ws://localhost:8090/rsocket"))
    			.block();
    }
}
