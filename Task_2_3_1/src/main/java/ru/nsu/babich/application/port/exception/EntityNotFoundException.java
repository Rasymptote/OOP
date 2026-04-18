package ru.nsu.babich.application.port.exception;

/**
 * Exception indicating that requested persisted entity does not exist.
 */
public class EntityNotFoundException extends RepositoryException {
    /**
     * Creates exception with message.
     *
     * @param message Error description.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
