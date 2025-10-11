package ru.nsu.babich.exceptions;

import ru.nsu.babich.Edge;

/**
 * An exception occurred when graph does not have the specified edge.
 */
public class GraphEdgeException extends GraphException {

    /**
     * Creates an exception.
     *
     * @param edge A missing edge.
     */
    public GraphEdgeException(Edge edge) {
        super(String.format("No such edge: %s", edge));
    }
}
