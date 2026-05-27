package ru.nsu.babich.server.domain.model;

/**
 * Immutable two-dimensional point on the game board.
 *
 * @param x Horizontal coordinate.
 * @param y Vertical coordinate.
 */
public record Point(int x, int y) {
}
