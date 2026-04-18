package ru.nsu.babich.presentation.view.model.renderable;

import java.util.List;
import ru.nsu.babich.presentation.view.model.figure.Figure;

/**
 * Provides conversion to drawable figures.
 */
public interface Renderable {

    /**
     * Converts object into renderable figures.
     *
     * @return Figures for rendering.
     */
    List<Figure> toFigures();

}
