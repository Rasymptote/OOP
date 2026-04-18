package ru.nsu.babich.application.dto;

import java.util.List;

/**
 * Data transfer object describing snake state.
 *
 * @param segments Ordered list of snake segments from head to tail.
 * @param pendingGrowthTicks Number of future ticks when snake keeps growing.
 */
public record SnakeDto(List<PointDto> segments, int pendingGrowthTicks) {
}
