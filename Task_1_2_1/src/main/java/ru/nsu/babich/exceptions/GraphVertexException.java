package ru.nsu.babich.exceptions;

import ru.nsu.babich.Vertex;

/**
 * An exception occurred when graph does not have the specified vertex.
 */
public class GraphVertexException extends GraphException {

    /**
     * Creates an exception.
     *
     * @param vertex A missing vertex.
     */
    public GraphVertexException(Vertex vertex) {
        super(String.format("No such vertex: %s", vertex));
    }
}
