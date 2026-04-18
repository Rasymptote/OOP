package ru.nsu.babich.domain.service.strategy.cellpicking;

import java.util.List;
import ru.nsu.babich.domain.model.Point;

/**
 * Strategy for selecting a cell from available candidates.
 */
public interface CellPickingStrategy {

    /**
     * Selects one cell from the provided candidate list.
     *
     * @param cells Candidate cells.
     * @return Selected cell.
     */
    Point pick(List<Point> cells);
}
