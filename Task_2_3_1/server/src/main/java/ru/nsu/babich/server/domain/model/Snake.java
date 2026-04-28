package ru.nsu.babich.server.domain.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * An immutable snake model.
 */
public class Snake {

    private final Deque<Point> segments;
    private final int pendingGrowthTicks;

    /**
     * Constructs a new Snake instance.
     *
     * @param segments The segments of the snake.
     * @param pendingGrowthTicks The number of ticks the snake will grow for.
     */
    public Snake(Deque<Point> segments, int pendingGrowthTicks) {
        Objects.requireNonNull(segments, "segments must not be null");
        if (segments.isEmpty()) {
            throw new IllegalArgumentException("segments must not be empty");
        }
        if (segments.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("segments must not contain nulls");
        }
        if (pendingGrowthTicks < 0) {
            throw new IllegalArgumentException("pendingGrowthTicks must be non-negative");
        }
        this.segments = new ArrayDeque<>(segments);
        this.pendingGrowthTicks = pendingGrowthTicks;
    }

    /**
     * Moves the snake to a new head position, adding a new head segment and removing the tail segment if not growing.
     *
     * @param newHead A new head position.
     * @return A new Snake instance with the updated segments and growth state.
     */
    public Snake move(Point newHead) {
        Objects.requireNonNull(newHead, "newHead must not be null");
        Deque<Point> newSegments = new ArrayDeque<>(segments);
        newSegments.addFirst(newHead);

        int newGrowth = pendingGrowthTicks;
        if (newGrowth > 0) {
            newGrowth--;
        } else {
            newSegments.removeLast();
        }

        return new Snake(newSegments, newGrowth);
    }

    /**
     * Returns a snake state with additional pending growth.
     *
     * @param ticksToAdd The number of growth ticks to add.
     * @return A new Snake instance with the updated growth ticks.
     */
    public Snake withAddedGrowthTicks(int ticksToAdd) {
        if (ticksToAdd < 0) {
            throw new IllegalArgumentException("ticksToAdd must be non-negative");
        }
        return new Snake(segments, pendingGrowthTicks + ticksToAdd);
    }

    /**
     * Returns the current head position.
     *
     * @return The head segment of the snake.
     */
    public Point getHead() {
        return segments.getFirst();
    }

    /**
     * Returns all body segments except the head.
     *
     * @return A list of body segments of the snake, excluding the head.
     */
    public List<Point> getBody() {
        return segments.stream().skip(1).toList();
    }

    /**
     * Returns all segments from head to tail.
     *
     * @return Segments of the snake.
     */
    public List<Point> getSegments() {
        return List.copyOf(segments);
    }

    public int getPendingGrowthTicks() {
        return pendingGrowthTicks;
    }
}
