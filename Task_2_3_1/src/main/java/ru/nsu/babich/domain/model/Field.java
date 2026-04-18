package ru.nsu.babich.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable game field dimensions and utility operations.
 *
 * @param rows Number of field rows.
 * @param columns Number of field columns.
 */
public record Field(int rows, int columns) {

    public Field {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Field size must be positive");
        }
    }

    /**
     * Checks whether a point lies inside field bounds.
     *
     * @param point Point to validate.
     * @return {@code true} if the point is inside the field.
     */
    public boolean isInside(Point point) {
        Objects.requireNonNull(point, "point must not be null");
        return point.x() >= 0 && point.x() < columns
                && point.y() >= 0 && point.y() < rows;
    }

    /**
     * Returns all currently unoccupied cells.
     *
     * @param snake Snake occupying part of the field.
     * @param foods Food items occupying cells.
     * @return List of free cells.
     */
    public List<Point> getFreeCells(Snake snake, List<Food> foods) {
        Objects.requireNonNull(snake, "snake must not be null");
        Objects.requireNonNull(foods, "foods must not be null");

        Set<Point> occupied = new HashSet<>(snake.getSegments());
        foods.stream()
                .map(Food::position)
                .forEach(occupied::add);

        List<Point> free = new ArrayList<>();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Point p = new Point(x, y);
                if (!occupied.contains(p)) {
                    free.add(p);
                }
            }
        }

        return free;
    }
}
