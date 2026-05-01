package ru.nsu.babich.server.presentation;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.nsu.babich.server.application.usecase.LeavePlayerUseCase;
import ru.nsu.babich.server.config.StompRoutes;
import ru.nsu.babich.server.presentation.mapper.GameStateMapper;

/**
 * Listens to WebSocket session events and performs cleanup / notifications.
 */
@Component
public class WebSocketEventListener {

    private final LeavePlayerUseCase leavePlayerUseCase;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameStateMapper gameStateMapper;

    /**
     * Constructs event listener with required dependencies.
     *
     * @param leavePlayerUseCase Use case that handles player leaving logic
     *                           when session disconnects.
     * @param messagingTemplate Spring component used to send messages to STOMP topics.
     * @param gameStateMapper Mapper that converts domain game state to DTO.
     */
    public WebSocketEventListener(LeavePlayerUseCase leavePlayerUseCase,
                                  SimpMessagingTemplate messagingTemplate,
                                  GameStateMapper gameStateMapper) {
        this.leavePlayerUseCase = leavePlayerUseCase;
        this.messagingTemplate = messagingTemplate;
        this.gameStateMapper = gameStateMapper;
    }

    /**
     * Handles WebSocket session disconnect events by invoking player leave logic.
     *
     * @param event Session disconnect event containing session information.
     */
    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        var sessionId = event.getSessionId();
        var state = leavePlayerUseCase.execute(sessionId);
        messagingTemplate.convertAndSend(StompRoutes.STATE_TOPIC, gameStateMapper.toDto(state));
    }
}
