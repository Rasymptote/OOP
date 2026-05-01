package ru.nsu.babich.server.application.usecase;

import java.util.ArrayList;
import java.util.Objects;
import ru.nsu.babich.server.application.exception.UseCaseException;
import ru.nsu.babich.server.application.exception.ValidationException;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.SessionRepository;
import ru.nsu.babich.server.domain.model.Direction;
import ru.nsu.babich.server.domain.model.GameState;

/**
 * Changes movement direction for a player identified by session id.
 */
public class ChangeDirectionUseCase {
    private final GameStateRepository gameStateRepository;
    private final SessionRepository sessionRepository;

    public ChangeDirectionUseCase(GameStateRepository gameStateRepository, SessionRepository sessionRepository) {
        this.gameStateRepository = Objects.requireNonNull(gameStateRepository,
                "gameStateRepository must not be null");
        this.sessionRepository = Objects.requireNonNull(sessionRepository,
                "sessionRepository must not be null");
    }

    /**
     * Applies new direction for the session player and persists updated state.
     *
     * @param sessionId Session identifier.
     * @param direction New direction.
     */
    public void execute(String sessionId, Direction direction) {
        try {
            var playerId = sessionRepository.getPlayerId(sessionId);
            var state = gameStateRepository.load();
            var players = new ArrayList<>(state.players());
            var player = players.stream()
                    .filter(p -> p.id().equals(playerId))
                    .findFirst()
                    .orElseThrow(() -> new ValidationException("Player not found"));

            player.movementStrategy().putDirection(direction);
            var newState = new GameState(
                    state.field(),
                    players,
                    state.foods()
            );
            gameStateRepository.save(newState);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new UseCaseException("Failed to change player direction", e);
        }
    }
}
