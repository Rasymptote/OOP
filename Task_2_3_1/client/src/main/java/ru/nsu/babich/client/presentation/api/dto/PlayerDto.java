package ru.nsu.babich.client.presentation.api.dto;

/**
 * Data transfer object describing player state.
 *
 * @param id Player identifier.
 * @param snake Snake state.
 * @param score Current player score.
 */
public record PlayerDto(
        String id,
        SnakeDto snake,
        int score
) {
}
