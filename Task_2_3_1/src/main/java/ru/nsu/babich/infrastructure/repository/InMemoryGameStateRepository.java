package ru.nsu.babich.infrastructure.repository;

import java.util.Objects;
import ru.nsu.babich.application.port.GameStateRepository;
import ru.nsu.babich.application.port.exception.EntityNotFoundException;
import ru.nsu.babich.domain.model.GameState;

public class InMemoryGameStateRepository implements GameStateRepository {
    private GameState gameState;

    @Override
    public void save(GameState gameState) {
        this.gameState = Objects.requireNonNull(gameState, "gameState must not be null");
    }

    @Override
    public GameState load() throws EntityNotFoundException {
        if (gameState == null) {
            throw new EntityNotFoundException("Game state not found");
        }
        return gameState;
    }
}
