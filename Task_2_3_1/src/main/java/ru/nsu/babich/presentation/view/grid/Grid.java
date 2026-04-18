package ru.nsu.babich.presentation.view.grid;

/**
 * Provides grid dimensions and pixel mapping metrics.
 */
public interface Grid {
    /**
     * Returns the number of rows in the grid.
     *
     * @return Rows count.
     */
    int getRowsCount();

    /**
     * Returns the number of columns in the grid.
     *
     * @return Columns count.
     */
    int getColumnsCount();

    /**
     * Returns current cell size in pixels.
     *
     * @return Cell size.
     */
    double getCellSize();

    /**
     * Returns horizontal offset used to center the grid.
     *
     * @return Horizontal offset.
     */
    double getHorizontalOffset();

    /**
     * Returns vertical offset used to center the grid.
     *
     * @return Vertical offset.
     */
    double getVerticalOffset();
}
