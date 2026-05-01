package ru.nsu.babich.client.presentation.view.model.figure;

import javafx.scene.paint.Paint;
import ru.nsu.babich.client.presentation.api.dto.PointDto;

/**
 * Describes a primitive figure for rendering.
 *
 * @param type Figure type.
 * @param cell Target grid cell.
 * @param scale Size scale relative to cell size.
 * @param color Figure fill color.
 */
public record Figure(FigureType type, PointDto cell, double scale, Paint color) {
}
