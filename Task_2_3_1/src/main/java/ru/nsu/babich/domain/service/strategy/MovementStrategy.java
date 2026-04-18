package ru.nsu.babich.domain.service.strategy;

import ru.nsu.babich.domain.model.Point;

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
}
