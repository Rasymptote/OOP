package ru.nsu.babich.server.application.usecase;

import java.util.ArrayList;
import java.util.Objects;
import ru.nsu.babich.server.application.exception.UseCaseException;
import ru.nsu.babich.server.application.exception.ValidationException;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.SessionRepository;
import ru.nsu.babich.server.domain.factory.PlayerFactory;
import ru.nsu.babich.server.domain.model.GameState;
import ru.nsu.babich.server.domain.service.FreeCellsService;
import ru.nsu.babich.server.domain.service.strategy.movement.MovementStrategy;

/**
 * Adds a new player to the current game state.
 */
public class JoinPlayerUseCase {
    private final GameStateRepository gameStateRepository;
    private final SessionRepository sessionRepository;
    private final FreeCellsService freeCellsService;
    private final PlayerFactory playerFactory;

    public JoinPlayerUseCase(GameStateRepository gameStateRepository,
                             SessionRepository sessionRepository,
                             FreeCellsService freeCellsService,
                             PlayerFactory playerFactory) {
        this.gameStateRepository = Objects.requireNonNull(gameStateRepository,
                "gameStateRepository must not be null");
        this.sessionRepository = Objects.requireNonNull(sessionRepository,
                "sessionRepository must not be null");
        this.freeCellsService = Objects.requireNonNull(freeCellsService,
                "freeCellsService must not be null");
        this.playerFactory = Objects.requireNonNull(playerFactory,
                "playerFactory must not be null");
    }

    /**
     * Creates and registers a new player for the given session.
     *
     * @param sessionId Session identifier.
     * @param strategy Movement strategy for the new player.
     */
    public void execute(String sessionId, MovementStrategy strategy) {
        try {
            System.out.println(sessionId);
            var resultingState = gameStateRepository.update(current -> {
                var freeCells = freeCellsService.getFreeCells(
                        current.field(), current.players(), current.foods()
                );
                if (freeCells.isEmpty()) {
                    throw new ValidationException("Cannot join player: no free cells available");
                }
                var freeCell = freeCells.get(0);
                var newPlayer = playerFactory.create(strategy, freeCell);
                var nextPlayers = new ArrayList<>(current.players());
                nextPlayers.add(newPlayer);
                sessionRepository.save(sessionId, newPlayer.id());
                return new GameState(current.field(), nextPlayers, current.foods());
            });
            System.out.println(resultingState);

        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new UseCaseException("Failed to join player due to unexpected error", e);
        }
    }
}