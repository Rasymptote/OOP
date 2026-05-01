package ru.nsu.babich.server.domain.model;

/**
 * Represents movement directions on the game field.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Returns direction opposite to the current one.
     *
     * @return Opposite direction.
     */
    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
