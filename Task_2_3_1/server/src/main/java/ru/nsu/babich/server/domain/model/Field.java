package ru.nsu.babich.server.domain.model;

import java.util.Objects;

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
}

