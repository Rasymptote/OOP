package ru.nsu.babich.server.domain.service.strategy.movement;

import java.util.ArrayDeque;
import java.util.Deque;
import ru.nsu.babich.server.domain.model.Direction;
import ru.nsu.babich.server.domain.model.Point;

public class BufferedMovementStrategy implements MovementStrategy {
    private final Deque<Direction> buffer;
    private final int bufferSize;
    private Direction lastDirection;

    public BufferedMovementStrategy(int bufferSize) {
        buffer = new ArrayDeque<>();
        lastDirection = Direction.RIGHT;
        this.bufferSize = bufferSize;
    }

    @Override
    public Point computeNextHead(Point currentHead) {
        if (!buffer.isEmpty()) {
            lastDirection = buffer.removeFirst();
        }
        return switch (lastDirection) {
            case UP -> new Point(currentHead.x(), currentHead.y() - 1);
            case RIGHT -> new Point(currentHead.x() + 1, currentHead.y());
            case DOWN -> new Point(currentHead.x(), currentHead.y() + 1);
            case LEFT -> new Point(currentHead.x() - 1, currentHead.y());
        };
    }

    @Override
    public void putDirection(Direction direction) {
        if (buffer.size() < bufferSize) {
            var lastInputDirection = buffer.isEmpty() ? lastDirection : buffer.getLast();
            if (lastInputDirection != direction && lastInputDirection.opposite() != direction) {
                buffer.addLast(direction);
            }
        }
    }
}
