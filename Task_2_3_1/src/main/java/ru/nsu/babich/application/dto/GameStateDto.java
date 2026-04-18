package ru.nsu.babich.application.dto;

import java.util.List;

/**
 * Data transfer object describing full game state configuration.
 *
 * @param field Field dimensions.
 * @param snake Snake state.
 * @param foods Food items currently placed on the field.
 * @param status Game status name.
 */
public record GameStateDto(
        FieldDto field,
        SnakeDto snake,
        List<FoodDto> foods,
        String status
) {
}
