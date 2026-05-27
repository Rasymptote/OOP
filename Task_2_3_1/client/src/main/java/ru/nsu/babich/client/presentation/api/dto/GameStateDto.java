package ru.nsu.babich.client.presentation.api.dto;

import java.util.List;

/**
 * Data transfer object describing full game state configuration.
 *
 * @param field Field dimensions.
 * @param players Players currently in the game.
 * @param foods Food items currently placed on the field.
 */
public record GameStateDto(
        FieldDto field,
        List<PlayerDto> players,
        List<FoodDto> foods
) {
}
