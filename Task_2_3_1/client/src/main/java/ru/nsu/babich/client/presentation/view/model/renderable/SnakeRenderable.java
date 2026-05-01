package ru.nsu.babich.client.presentation.view.model.renderable;

import java.util.List;
import ru.nsu.babich.client.presentation.api.dto.PointDto;
import ru.nsu.babich.client.presentation.view.model.figure.Figure;
import ru.nsu.babich.client.presentation.view.model.figure.FigureType;

/**
 * Adapts snake segments for rendering.
 *
 * @param segments Snake body segments.
 */
public record SnakeRenderable(List<PointDto> segments) implements Renderable {
    /** {@inheritDoc} */
    @Override
    public List<Figure> toFigures() {
        return segments().stream()
                .map(cell -> new Figure(FigureType.SQUARE, cell, 0.8, RenderablePalette.SNAKE))
                .toList();
    }
}
