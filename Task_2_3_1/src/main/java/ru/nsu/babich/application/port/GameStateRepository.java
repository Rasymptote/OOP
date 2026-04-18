package ru.nsu.babich.application.port;

import ru.nsu.babich.application.port.exception.EntityNotFoundException;
import ru.nsu.babich.domain.model.GameState;

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
}
