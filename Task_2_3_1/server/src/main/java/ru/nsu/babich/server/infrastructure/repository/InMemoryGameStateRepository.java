package ru.nsu.babich.server.infrastructure.repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.exception.EntityNotFoundException;
import ru.nsu.babich.server.application.port.exception.RepositoryException;
import ru.nsu.babich.server.domain.model.Field;
import ru.nsu.babich.server.domain.model.GameState;

/**
 * In-memory implementation of game state storage.
 */
public class InMemoryGameStateRepository implements GameStateRepository {
    private final AtomicReference<GameState> stateRef = new AtomicReference<>();
    private final int width;
    private final int height;

    public InMemoryGameStateRepository(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Initializes repository with empty state for configured field dimensions.
     */
    public void init() {
        Field field = new Field(width, height);
        stateRef.set(new GameState(field, List.of(), List.of()));
    }

    /** {@inheritDoc} */
    @Override
    public GameState load() {
        GameState current = stateRef.get();
        if (current == null) {
            throw new EntityNotFoundException("Game state not initialized");
        }
        return current;
    }

    /** {@inheritDoc} */
    @Override
    public void save(GameState newState) {
        Objects.requireNonNull(newState, "newState must not be null");
        stateRef.set(newState);
    }

    /** {@inheritDoc} */
    @Override
    public GameState update(UnaryOperator<GameState> updater) {
        Objects.requireNonNull(updater, "updater must not be null");
        return stateRef.updateAndGet(current -> {
            if (current == null) {
                throw new EntityNotFoundException("Game state not initialized");
            }
            GameState updated = updater.apply(current);
            if (updated == null) {
                throw new RepositoryException("Updater must not return null");
            }
            return updated;
        });
    }
}
