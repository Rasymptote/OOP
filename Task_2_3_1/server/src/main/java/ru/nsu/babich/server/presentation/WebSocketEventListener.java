package ru.nsu.babich.server.presentation;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.nsu.babich.server.application.usecase.LeavePlayerUseCase;
import ru.nsu.babich.server.presentation.mapper.GameStateMapper;
import ru.nsu.babich.server.config.StompRoutes;

/**
 * Listens to WebSocket session events and performs cleanup / notifications.
 */
@Component
public class WebSocketEventListener {

    private final LeavePlayerUseCase leavePlayerUseCase;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameStateMapper gameStateMapper;

    public WebSocketEventListener(LeavePlayerUseCase leavePlayerUseCase,
                                  SimpMessagingTemplate messagingTemplate,
                                  GameStateMapper gameStateMapper) {
        this.leavePlayerUseCase = leavePlayerUseCase;
        this.messagingTemplate = messagingTemplate;
        this.gameStateMapper = gameStateMapper;
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        var sessionId = event.getSessionId();
        var state = leavePlayerUseCase.execute(sessionId);
        messagingTemplate.convertAndSend(StompRoutes.STATE_TOPIC, gameStateMapper.toDto(state));
    }
}
