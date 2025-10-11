package ru.nsu.babich.exceptions;

/**
 * An exception occurred when a cycle is detected in a graph during topological sorting.
 */
public class GraphCycleException extends GraphException {

    /**
     * Creates an exception.
     */
    public GraphCycleException() {
        super("Graph contains a cycle. Topological sorting is impossible.");
    }
}
