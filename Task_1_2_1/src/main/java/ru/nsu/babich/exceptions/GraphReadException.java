package ru.nsu.babich.exceptions;

/**
 * An exception occurred when failed to read graph data.
 */
public class GraphReadException extends GraphException {

    /**
     * Creates an exception.
     *
     * @param message Exception message.
     */
    public GraphReadException(String message) {
        super(message);
    }
}
