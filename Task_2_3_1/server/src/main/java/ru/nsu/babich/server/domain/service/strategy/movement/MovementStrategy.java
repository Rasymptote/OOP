package ru.nsu.babich.server.domain.service.strategy.movement;

import ru.nsu.babich.server.domain.model.Direction;
import ru.nsu.babich.server.domain.model.Point;

/**
 * Strategy for calculating the next snake head position.
 */
public interface MovementStrategy {

    /**
     * Computes the next head position from the current one.
     *
     * @param currentHead Current snake head.
     * @return Next head position.
     */
    Point computeNextHead(Point currentHead);

    /**
     * Accepts requested direction change before the next movement step.
     *
     * @param direction Requested direction.
     */
    default void putDirection(Direction direction) {
    }
}
