package ru.nsu.babich.presentation.view;

import javafx.scene.canvas.GraphicsContext;
import ru.nsu.babich.presentation.view.model.figure.Figure;
import ru.nsu.babich.presentation.view.grid.Grid;

/**
 * Renders a single view figure on the game canvas using grid metrics.
 */
public class FigureRenderer {

    /**
     * Draws the given figure in the corresponding grid cell.
     *
     * @param context Graphics context used for drawing.
     * @param grid Grid metrics used to convert cells into pixels.
     * @param figure Figure to render.
     */
    public void render(GraphicsContext context, Grid grid, Figure figure) {
        var scaledCellSize = grid.getCellSize() * figure.scale();
        var centeringOffset = (grid.getCellSize() - scaledCellSize) / 2;
        context.setFill(figure.color());
        var x = grid.getHorizontalOffset() + figure.cell().x() * grid.getCellSize() + centeringOffset;
        var y = grid.getVerticalOffset() + figure.cell().y() * grid.getCellSize() + centeringOffset;
        switch (figure.type()) {
            case CIRCLE -> context.fillOval(x, y, scaledCellSize, scaledCellSize);
            case SQUARE -> context.fillRect(x, y, scaledCellSize, scaledCellSize);
        }
    }
}
