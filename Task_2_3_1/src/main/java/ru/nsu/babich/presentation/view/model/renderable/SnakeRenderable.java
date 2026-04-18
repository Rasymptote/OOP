package ru.nsu.babich.presentation.view.model.renderable;

import java.util.List;
import javafx.scene.paint.Color;
import ru.nsu.babich.domain.model.Point;
import ru.nsu.babich.presentation.view.model.figure.Figure;
import ru.nsu.babich.presentation.view.model.figure.FigureType;

/**
 * Adapts snake segments for rendering.
 *
 * @param segments Snake body segments.
 */
public record SnakeRenderable(List<Point> segments) implements Renderable {
    /** {@inheritDoc} */
    @Override
    public List<Figure> toFigures() {
        return segments().stream()
                .map(cell -> new Figure(FigureType.SQUARE, cell, 0.8, Color.DEEPPINK))
                .toList();
    }
}
