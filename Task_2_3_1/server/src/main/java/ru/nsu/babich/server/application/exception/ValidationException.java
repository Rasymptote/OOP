package ru.nsu.babich.server.application.exception;

/**
 * Exception raised when input validation for application layer fails.
 */
public class ValidationException extends UseCaseException {
    /**
     * Creates validation exception with message.
     *
     * @param message Validation error description.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Creates validation exception with message and cause.
     *
     * @param message Validation error description.
     * @param cause Root cause.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

