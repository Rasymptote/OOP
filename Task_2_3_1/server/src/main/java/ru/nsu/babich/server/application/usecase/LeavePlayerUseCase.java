package ru.nsu.babich.server.application.usecase;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Component;
import ru.nsu.babich.server.application.exception.UseCaseException;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.SessionRepository;
import ru.nsu.babich.server.domain.model.GameState;

/**
 * Removes a player from the game by session id.
 */
@Component
public class LeavePlayerUseCase {
    private final SessionRepository sessionRepository;
    private final GameStateRepository gameStateRepository;

    /**
     * Constructs use case with required dependencies.
     *
     * @param sessionRepository Repository used to track player sessions.
     * @param gameStateRepository Repository used to load and save game state.
     */
    public LeavePlayerUseCase(SessionRepository sessionRepository,
                              GameStateRepository gameStateRepository) {
        this.sessionRepository = Objects.requireNonNull(sessionRepository,
                "sessionRepository must not be null");
        this.gameStateRepository = Objects.requireNonNull(gameStateRepository,
                "gameStateRepository must not be null");
    }

    /**
     * Removes a player from the game by their id.
     *
     * @param sessionId session id of the player to remove
     * @return updated GameState
     * @throws UseCaseException for repository errors
     */
    public GameState execute(String sessionId) {
        try {
            var playerId = sessionRepository.getPlayerId(sessionId);

            if (playerId == null) {
                return gameStateRepository.load();
            }

            return gameStateRepository.update(current -> {
                var players = new ArrayList<>(current.players());
                players.removeIf(player -> player.id().equals(playerId));
                return new GameState(current.field(), players, current.foods());
            });
        } catch (Exception e) {
            throw new UseCaseException("Failed to remove player", e);
        } finally {
            try {
                sessionRepository.remove(sessionId);
            } catch (Exception ignored) {
            }
        }
    }
}
