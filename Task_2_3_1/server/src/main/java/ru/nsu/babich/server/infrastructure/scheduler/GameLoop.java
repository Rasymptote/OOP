package ru.nsu.babich.server.infrastructure.scheduler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nsu.babich.server.application.usecase.GameTickUseCase;
import ru.nsu.babich.server.config.StompRoutes;
import ru.nsu.babich.server.presentation.mapper.GameStateMapper;

/**
 * Runs periodic game ticks and publishes updated game state to subscribers.
 */
@Component
public class GameLoop {
    private final GameTickUseCase gameTickUseCase;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameStateMapper gameStateMapper;

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
        messagingTemplate.convertAndSend(StompRoutes.STATE_TOPIC, gameStateMapper.toDto(state));
    }
}
