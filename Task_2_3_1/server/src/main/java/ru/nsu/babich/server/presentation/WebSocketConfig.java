package ru.nsu.babich.server.presentation;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.nsu.babich.server.config.StompRoutes;

/**
 * Configures STOMP over WebSocket endpoints and broker routes.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * {@inheritDoc}
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(StompRoutes.TOPIC_PREFIX);
        config.setApplicationDestinationPrefixes(StompRoutes.APP_PREFIX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(StompRoutes.WS_ENDPOINT).setAllowedOriginPatterns("*");
    }
}
