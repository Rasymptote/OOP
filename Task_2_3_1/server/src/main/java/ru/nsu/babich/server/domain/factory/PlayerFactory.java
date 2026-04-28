package ru.nsu.babich.server.domain.factory;

import java.util.ArrayDeque;
import java.util.UUID;
import ru.nsu.babich.server.domain.model.Player;
import ru.nsu.babich.server.domain.model.PlayerId;
import ru.nsu.babich.server.domain.model.Snake;
import ru.nsu.babich.server.domain.service.strategy.movement.MovementStrategy;
import ru.nsu.babich.server.domain.model.Point;

public class PlayerFactory {

    public Player create(MovementStrategy movementStrategy, Point startPosition) {
        String id = UUID.randomUUID().toString();
        var snake = new Snake(new ArrayDeque<>(), 0);
        snake.getBody().add(startPosition);
        return new Player(new PlayerId(id), movementStrategy, snake, 0);
    }
}