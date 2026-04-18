package ru.nsu.babich.domain.service;

import java.util.Objects;
import ru.nsu.babich.domain.model.Snake;
import ru.nsu.babich.domain.service.strategy.MovementStrategy;

/**
 * Domain service that applies one snake movement step.
 */
public class MovementService {
    private final MovementStrategy movementStrategy;

    /**
     * Constructs a moving service.
     *
     * @param movementStrategy Strategy used to compute the next head position.
     */
    public MovementService(MovementStrategy movementStrategy) {
        this.movementStrategy = Objects.requireNonNull(movementStrategy, "movementStrategy must not be null");
    }

    /**
     * Moves the snake according to the configured movement strategy.
     *
     * @param snake Current snake state.
     * @return Updated snake state after one movement step.
     */
    public Snake handle(Snake snake) {
        Objects.requireNonNull(snake, "snake must not be null");
        var nextHead = movementStrategy.computeNextHead(snake.getHead());
        return snake.move(nextHead);
    }
}
