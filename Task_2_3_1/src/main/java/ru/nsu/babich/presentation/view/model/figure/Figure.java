package ru.nsu.babich.presentation.view.model.figure;

import javafx.scene.paint.Paint;
import ru.nsu.babich.domain.model.Point;

/**
 * Describes a primitive figure for rendering.
 *
 * @param type Figure type.
 * @param cell Target grid cell.
 * @param scale Size scale relative to cell size.
 * @param color Figure fill color.
 */
public record Figure(FigureType type, Point cell, double scale, Paint color) {
}
