package ru.nsu.babich.server.application.port;

import java.util.function.UnaryOperator;
import ru.nsu.babich.server.application.port.exception.EntityNotFoundException;
import ru.nsu.babich.server.domain.model.GameState;

/**
 * Repository port for persisting and restoring game state.
 */
public interface GameStateRepository {

    /**
     * Saves game state.
     *
     * @param gameState State to persist.
     */
    void save(GameState gameState);

    /**
     * Loads previously stored game state.
     *
     * @return Loaded game state.
     * @throws EntityNotFoundException If no state is available.
     */
    GameState load() throws EntityNotFoundException;

    /**
     * Atomically updates game state using provided updater function.
     * The updater receives current state and must return new state to persist.
     * Implementations should apply the update atomically to avoid races.
     *
     * @param updater function transforming current state to new state
     * @return new persisted state
     */
    GameState update(UnaryOperator<GameState> updater);
}
