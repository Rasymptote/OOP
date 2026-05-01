package ru.nsu.babich.server.application.usecase;

import java.util.Objects;
import ru.nsu.babich.server.application.exception.UseCaseException;
import ru.nsu.babich.server.application.exception.ValidationException;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.exception.RepositoryException;
import ru.nsu.babich.server.domain.model.GameState;
import ru.nsu.babich.server.domain.service.GameTickService;

/**
 * Application use case that advances persisted game state by one tick.
 */
public class GameTickUseCase {

    private final GameStateRepository gameStateRepository;
    private final GameTickService gameTickService;

    /**
     * Constructs use case with required dependencies.
     *
     * @param repository Repository used to load and save game state.
     * @param gameTickService Domain service that performs tick logic.
     */
    public GameTickUseCase(GameStateRepository repository,
                           GameTickService gameTickService) {
        this.gameStateRepository = Objects.requireNonNull(repository,
                "gameStateRepository must not be null");
        this.gameTickService = Objects.requireNonNull(gameTickService,
                "gameTickService must not be null");
    }

    /**
     * Executes one game tick on persisted state and stores updated value.
     *
     * @return Updated game state.
     * @throws ValidationException If loaded state is invalid.
     * @throws UseCaseException If repository or other runtime errors occur.
     */
    public GameState execute() {
        try {
            var state = gameStateRepository.load();
            var newState = gameTickService.tick(state);
            gameStateRepository.save(newState);
            return newState;
        } catch (ValidationException e) {
            throw e;
        } catch (RepositoryException e) {
            throw new UseCaseException("Failed to execute game tick due to repository error", e);
        } catch (RuntimeException e) {
            throw new UseCaseException("Failed to execute game tick", e);
        }
    }
}