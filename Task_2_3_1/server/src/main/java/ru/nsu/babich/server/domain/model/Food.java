package ru.nsu.babich.server.domain.model;

import java.util.Objects;

/**
 * A food item placed on the game field.
 *
 * @param position Position of the food on the board.
 * @param type Type of food that defines the growth effect.
 */
public record Food(Point position, FoodType type) {
    public Food {
        Objects.requireNonNull(position, "position must not be null");
        Objects.requireNonNull(type, "type must not be null");
    }
}
