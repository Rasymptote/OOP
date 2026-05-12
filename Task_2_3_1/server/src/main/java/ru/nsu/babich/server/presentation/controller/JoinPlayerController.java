package ru.nsu.babich.server.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.nsu.babich.server.application.usecase.JoinPlayerUseCase;
import ru.nsu.babich.server.config.SnakeStompRoutes;
import ru.nsu.babich.server.domain.service.strategy.movement.BufferedMovementStrategy;

/**
 * Handles player join messages.
 */
@Controller
public class JoinPlayerController {
    private final JoinPlayerUseCase joinPlayerUseCase;

    @Value("${game.player.buffer-size:5}")
    private int bufferSize;

    @Autowired
    public JoinPlayerController(JoinPlayerUseCase joinPlayerUseCase) {
        this.joinPlayerUseCase = joinPlayerUseCase;
    }

    /**
     * Registers player for current session with buffered movement strategy.
     *
     * @param header STOMP message header accessor.
     */
    @MessageMapping(SnakeStompRoutes.JOIN_MAPPING)
    public void onJoinPlayer(
            SimpMessageHeaderAccessor header
    ) {
        var sessionId = header.getSessionId();
        joinPlayerUseCase.execute(sessionId, new BufferedMovementStrategy(bufferSize));
    }
}
