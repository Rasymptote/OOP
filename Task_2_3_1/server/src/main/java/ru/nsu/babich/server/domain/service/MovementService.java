package ru.nsu.babich.server.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import ru.nsu.babich.server.domain.model.Snake;
import ru.nsu.babich.server.domain.service.strategy.movement.MovementStrategy;

/**
 * Domain service that applies one snake movement step.
 */
@Service
public class MovementService {
    /**
     * Moves the snake according to the movement strategy.
     *
     * @param snake Current snake state.
     * @param strategy Movement strategy to compute the next head position.
     * @return Updated snake state after one movement step.
     */
    public Snake handle(Snake snake, MovementStrategy strategy) {
        Objects.requireNonNull(snake, "snake must not be null");
        var nextHead = strategy.computeNextHead(snake.getHead());
        return snake.move(nextHead);
    }
}
