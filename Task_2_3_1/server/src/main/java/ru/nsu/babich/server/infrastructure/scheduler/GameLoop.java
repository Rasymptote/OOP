package ru.nsu.babich.server.infrastructure.scheduler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nsu.babich.server.application.usecase.GameTickUseCase;
import ru.nsu.babich.server.config.SnakeStompRoutes;
import ru.nsu.babich.server.presentation.mapper.GameStateMapper;

/**
 * Runs periodic game ticks and publishes updated game state to subscribers.
 */
@Component
public class GameLoop {
    private final GameTickUseCase gameTickUseCase;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameStateMapper gameStateMapper;

    /**
     * Constructs game loop with required dependencies.
     *
     * @param gameTickUseCase Use case that performs game tick logic.
     * @param messagingTemplate Spring component used to send messages to STOMP topics.
     * @param gameStateMapper Mapper that converts domain game state to DTO for broadcasting.
     */
    public GameLoop(GameTickUseCase gameTickUseCase,
                    SimpMessagingTemplate messagingTemplate,
                    GameStateMapper gameStateMapper) {
        this.gameTickUseCase = gameTickUseCase;
        this.messagingTemplate = messagingTemplate;
        this.gameStateMapper = gameStateMapper;
    }

    /**
     * Executes one game tick and broadcasts resulting state.
     */
    @Scheduled(fixedRateString = "${game.tick-duration:1000}")
    public void runTick() {
        var state = gameTickUseCase.execute();
        messagingTemplate.convertAndSend(SnakeStompRoutes.STATE_TOPIC, gameStateMapper.toDto(state));
    }
}
