package ru.nsu.babich.application.port.exception;

/**
 * Base unchecked exception for repository access failures.
 */
public class RepositoryException extends RuntimeException {
    /**
     * Creates repository exception with message.
     *
     * @param message Error description.
     */
    public RepositoryException(String message) {
        super(message);
    }
}
