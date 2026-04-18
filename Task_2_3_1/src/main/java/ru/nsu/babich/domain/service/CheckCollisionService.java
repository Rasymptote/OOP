package ru.nsu.babich.domain.service;

import java.util.Objects;
import ru.nsu.babich.domain.model.Field;
import ru.nsu.babich.domain.model.Point;
import ru.nsu.babich.domain.model.Snake;

/**
 * Domain service for detecting snake collisions.
 */
public class CheckCollisionService {

    /**
     * Checks whether the snake collides with walls or with its own body.
     *
     * @param snake Current snake state.
     * @param field Active game board.
     * @return {@code true} if a collision is detected.
     */
    public boolean hasCollision(Snake snake, Field field) {
        Objects.requireNonNull(snake, "snake must not be null");
        Objects.requireNonNull(field, "field must not be null");
        return !field.isInside(snake.getHead()) || hasSelfCollision(snake);
    }

    /**
     * Checks whether two points refer to the same field cell.
     *
     * @param first First point.
     * @param second Second point.
     * @return {@code true} when both points are equal.
     */
    public boolean isSameCell(Point first, Point second) {
        Objects.requireNonNull(first, "first point must not be null");
        Objects.requireNonNull(second, "second point must not be null");
        return first.equals(second);
    }

    private boolean hasSelfCollision(Snake snake) {
        var head = snake.getHead();
        return snake.getBody().stream().anyMatch(segment -> isSameCell(head, segment));
    }

}
