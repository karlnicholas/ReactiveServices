package com.github.karlnicholas.rs.datagenerator.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import lombok.extern.slf4j.Slf4j;

@Configuration
public class RSocketConfig {
	    @Bean
	    public RSocketRequester rSocketRequester(RSocketStrategies strategies) {
	    	return RSocketRequester.builder()
	    			.rsocketStrategies(strategies)
	    			.connectWebSocket(URI.create("http://localhost:8091/rsocket"))
	    			.block();
	    }
/*	
	private static MimeType COMPOSITE_METADATA =
			MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_COMPOSITE_METADATA.getString());
	
	@Bean
	RSocket rSocket() {
	    return RSocketFactory.connect()
	            .mimeType(WellKnownMimeType.MESSAGE_RSOCKET_COMPOSITE_METADATA.getString(), MimeTypeUtils.APPLICATION_JSON_VALUE)
	            .frameDecoder(PayloadDecoder.ZERO_COPY)
	            .transport(WebsocketClientTransport.create(URI.create("ws://localhost:8091/rsocket/")))
	            .start()
	            .block();
	}

	@Bean
	RSocketRequester requester(RSocketStrategies strategies) {
	    return RSocketRequester.wrap(rSocket(), MimeTypeUtils.APPLICATION_JSON, COMPOSITE_METADATA, strategies);
	}
*/	
}
