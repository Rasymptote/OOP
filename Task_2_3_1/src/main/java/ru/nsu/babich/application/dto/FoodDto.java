package ru.nsu.babich.application.dto;

/**
 * Data transfer object for a single food item on the field.
 *
 * @param x X-coordinate of food position.
 * @param y Y-coordinate of food position.
 * @param type Food type name.
 */
public record FoodDto(int x, int y, String type) {
}
