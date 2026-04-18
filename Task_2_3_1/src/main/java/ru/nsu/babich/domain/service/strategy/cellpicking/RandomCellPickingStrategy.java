package ru.nsu.babich.domain.service.strategy.cellpicking;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import ru.nsu.babich.domain.exception.NoFreeCellsException;
import ru.nsu.babich.domain.model.Point;

/**
 * Cell picking strategy that chooses a random free cell.
 */
public class RandomCellPickingStrategy implements CellPickingStrategy {

    private final Random random;

    /**
     * Constructs a random cell picking strategy.
     *
     * @param random Random source used for selection.
     */
    public RandomCellPickingStrategy(Random random) {
        this.random = Objects.requireNonNull(random, "random must not be null");
    }

    /**
     * Selects a random cell from the provided list.
     *
     * @param cells Candidate cells.
     * @return Randomly selected cell.
     * @throws NoFreeCellsException If the input list is empty.
     */
    @Override
    public Point pick(List<Point> cells) {
        Objects.requireNonNull(cells, "cells must not be null");
        if (cells.isEmpty()) {
            throw new NoFreeCellsException("No free cells for food generation");
        }
        return cells.get(random.nextInt(cells.size()));
    }
}
