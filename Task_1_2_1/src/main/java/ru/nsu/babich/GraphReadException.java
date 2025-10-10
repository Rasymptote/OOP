package ru.nsu.babich;

/**
 * An exception occurred when failed to read graph data.
 */
public class GraphReadException extends RuntimeException {

    /**
     * Creates an exception.
     *
     * @param message Exception message.
     */
    public GraphReadException(String message) {
        super(message);
    }
}
