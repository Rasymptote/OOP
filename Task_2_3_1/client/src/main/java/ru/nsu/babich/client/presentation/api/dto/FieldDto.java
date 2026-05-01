package ru.nsu.babich.client.presentation.api.dto;

/**
 * Data transfer object that describes game field dimensions.
 *
 * @param rows Number of rows in the field.
 * @param columns Number of columns in the field.
 */
public record FieldDto(int rows, int columns) {
}
