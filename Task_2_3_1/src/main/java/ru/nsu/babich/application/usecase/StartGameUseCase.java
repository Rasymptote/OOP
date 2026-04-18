package ru.nsu.babich.application.usecase;

import java.util.Objects;
import ru.nsu.babich.application.dto.GameStateDto;
import ru.nsu.babich.application.exception.UseCaseException;
import ru.nsu.babich.application.exception.ValidationException;
import ru.nsu.babich.application.mapper.GameStateMapper;
import ru.nsu.babich.application.port.GameStateRepository;
import ru.nsu.babich.application.port.exception.RepositoryException;
import ru.nsu.babich.domain.model.GameState;

/**
 * Application use case that validates initial configuration and starts a game.
 */
public class StartGameUseCase {
    private final GameStateRepository gameStateRepository;

    /**
     * Constructs use case with repository dependency.
     *
     * @param gameStateRepository Repository used to store initial game state.
     */
    public StartGameUseCase(GameStateRepository gameStateRepository) {
        this.gameStateRepository = Objects.requireNonNull(gameStateRepository, "gameStateRepository must not be null");
    }

    /**
     * Creates game state from DTO configuration and persists it.
     *
     * @param config Initial game configuration.
     * @return Persisted initial game state.
     * @throws ValidationException If configuration is invalid.
     * @throws UseCaseException If repository or other runtime errors occur.
     */
    public GameState execute(GameStateDto config) {
        if (config == null) {
            throw new ValidationException("gameState config must not be null");
        }
        try {
            var gameState = GameStateMapper.toModel(config);
            gameStateRepository.save(gameState);
            return gameState;
        } catch (ValidationException e) {
            throw e;
        } catch (RepositoryException e) {
            throw new UseCaseException("Failed to start game due to repository error", e);
        } catch (RuntimeException e) {
            throw new UseCaseException("Failed to start game", e);
        }
    }
}
