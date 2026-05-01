package ru.nsu.babich.client.presentation.view;

import java.util.List;
import ru.nsu.babich.client.presentation.view.grid.GridRenderer;
import ru.nsu.babich.client.presentation.view.model.renderable.Renderable;

/**
 * Renders a full game frame: grid first, then all renderable objects.
 */
public class GameRenderer {

    private final GridRenderer gridRenderer;
    private final FigureRenderer figureRenderer;

    /**
     * Creates a renderer with required grid and figure renderers.
     *
     * @param gridRenderer Grid renderer.
     * @param figureRenderer Figure renderer.
     */
    public GameRenderer(GridRenderer gridRenderer, FigureRenderer figureRenderer) {
        this.gridRenderer = gridRenderer;
        this.figureRenderer = figureRenderer;
    }

    /**
     * Renders all objects for the current frame.
     *
     * @param renderables Objects to render.
     */
    public void renderAll(List<Renderable> renderables) {
        gridRenderer.render();
        for (var renderable : renderables) {
            var figures = renderable.toFigures();
            for (var figure : figures) {
                figureRenderer.render(gridRenderer.getGraphicsContext(),
                        gridRenderer.getGrid(), figure);
            }
        }
    }
}
