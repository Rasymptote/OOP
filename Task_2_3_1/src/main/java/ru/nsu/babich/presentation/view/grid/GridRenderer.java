package ru.nsu.babich.presentation.view.grid;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Draws a centered checkerboard grid on an internal canvas.
 */
public class GridRenderer extends Pane implements Grid {

    private final Canvas canvas;
    private final IntegerProperty rowsCount = new SimpleIntegerProperty(0);
    private final IntegerProperty columnsCount = new SimpleIntegerProperty(0);
    private final ObjectProperty<Color> primaryColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Color> secondaryColor = new SimpleObjectProperty<>(Color.BLACK);

    /**
     * Creates a grid renderer with an empty canvas.
     */
    public GridRenderer() {
        this.canvas = new Canvas();
        getChildren().add(canvas);
    }

    /**
     * Renders board background and alternating cells.
     */
    public void render() {
        var context = canvas.getGraphicsContext2D();
        double cellSize = getCellSize();
        context.setFill(getPrimaryColor());
        context.fillRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        for (int row = 0; row < getRowsCount(); row++) {
            for (int col = row % 2; col < getColumnsCount(); col += 2) {
                context.setFill(getSecondaryColor());
                context.fillRect(getHorizontalOffset() + col * cellSize,
                        getVerticalOffset() + row * cellSize, cellSize, cellSize);
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public int getRowsCount() {
        return this.rowsCount.get();
    }

    /** {@inheritDoc} **/
    @Override
    public int getColumnsCount() {
        return this.columnsCount.get();
    }

    /** {@inheritDoc} **/
    @Override
    public double getCellSize() {
        if (getColumnsCount() == 0 || getRowsCount() == 0) {
            return 0;
        }
        return Math.min(
                getCanvas().getWidth() / getColumnsCount(),
                getCanvas().getHeight() / getRowsCount()
        );
    }

    /** {@inheritDoc} **/
    @Override
    public double getHorizontalOffset() {
        double fieldWidth = getCellSize() * getColumnsCount();
        return (getWidth() - fieldWidth) / 2;
    }

    /** {@inheritDoc} **/
    @Override
    public double getVerticalOffset() {
        double fieldHeight = getCellSize() * getRowsCount();
        return (getHeight() - fieldHeight) / 2;
    }

    /**
     * Returns the primary board color.
     *
     * @return Primary color.
     */
    public Color getPrimaryColor() {
        return primaryColor.get();
    }

    /**
     * Returns the secondary board color.
     *
     * @return Secondary color.
     */
    public Color getSecondaryColor() {
        return secondaryColor.get();
    }

    /**
     * Sets rows count.
     *
     * @param rowsCount Rows count.
     */
    public void setRowsCount(int rowsCount) {
        this.rowsCount.set(rowsCount);
    }

    /**
     * Sets columns count.
     *
     * @param columnsCount Columns count.
     */
    public void setColumnsCount(int columnsCount) {
        this.columnsCount.set(columnsCount);
    }

    /**
     * Sets primary board color.
     *
     * @param primaryColor Primary color.
     */
    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor.set(primaryColor);
    }

    /**
     * Sets secondary board color.
     *
     * @param secondaryColor Secondary color.
     */
    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor.set(secondaryColor);
    }

    /**
     * Returns internal drawing canvas.
     *
     * @return Canvas.
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * Returns graphics context of the internal canvas.
     *
     * @return Graphics context.
     */
    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    /**
     * Returns this renderer as grid metrics provider.
     *
     * @return Grid metrics provider.
     */
    public Grid getGrid() {
        return this;
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
    }
}
