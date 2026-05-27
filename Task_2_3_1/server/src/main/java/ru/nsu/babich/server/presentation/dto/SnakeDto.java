package ru.nsu.babich.server.presentation.dto;

import java.util.List;

/**
 * Data transfer object describing snake state.
 *
 * @param segments Ordered list of snake segments from head to tail.
 */
public record SnakeDto(List<PointDto> segments) {
}
