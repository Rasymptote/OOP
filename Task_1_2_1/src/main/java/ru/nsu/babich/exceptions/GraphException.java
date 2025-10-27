package ru.nsu.babich.exceptions;

/**
 * Basic graph exception.
 */
public class GraphException extends RuntimeException {

    /**
     * Creates an exception.
     *
     * @param message Exception message.
     */
    public GraphException(String message) {
        super(message);
    }
}
