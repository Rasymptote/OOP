package ru.nsu.babich.server.domain.factory;

import java.util.ArrayDeque;
import java.util.UUID;
import ru.nsu.babich.server.domain.model.Player;
import ru.nsu.babich.server.domain.model.PlayerId;
import ru.nsu.babich.server.domain.model.Point;
import ru.nsu.babich.server.domain.model.Snake;
import ru.nsu.babich.server.domain.service.strategy.movement.MovementStrategy;

/**
 * Factory for creating new player instances with default initial state.
 */
public class PlayerFactory {

    /**
     * Creates a new player at the specified start position.
     *
     * @param movementStrategy Movement strategy for the player.
     * @param startPosition Initial snake head position.
     * @return Created player.
     */
    public Player create(MovementStrategy movementStrategy, Point startPosition) {
        String id = UUID.randomUUID().toString();
        var body = new ArrayDeque<Point>();
        body.add(startPosition);
        var snake = new Snake(body, 0);
        return new Player(new PlayerId(id), movementStrategy, snake, 0);
    }
}