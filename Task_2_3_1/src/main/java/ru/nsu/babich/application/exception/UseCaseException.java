package ru.nsu.babich.application.exception;

/**
 * Base unchecked exception for application use case failures.
 */
public class UseCaseException extends RuntimeException {
    /**
     * Creates exception with message.
     *
     * @param message Error description.
     */
    public UseCaseException(String message) {
        super(message);
    }

    /**
     * Creates exception with message and original cause.
     *
     * @param message Error description.
     * @param cause Root cause.
     */
    public UseCaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

