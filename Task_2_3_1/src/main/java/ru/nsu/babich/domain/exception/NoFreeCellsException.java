package ru.nsu.babich.domain.exception;

/**
 * Thrown when no free cells are available for cell picking.
 */
public class NoFreeCellsException extends RuntimeException {

    public NoFreeCellsException(String message) {
        super(message);
    }
}

