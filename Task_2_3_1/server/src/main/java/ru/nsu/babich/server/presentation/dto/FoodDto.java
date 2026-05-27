package ru.nsu.babich.server.presentation.dto;

/**
 * Data transfer object for a single food item on the field.
 *
 * @param position Position of the food on the field.
 * @param type Food type name.
 */
public record FoodDto(PointDto position, String type) {
}
