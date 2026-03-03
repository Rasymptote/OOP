package ru.nsu.babich.logging;

/**
 * An interface for logging messages related to pizza orders.
 */
public interface OrderLogger {

    /**
     * Logs a message associated with a specific order ID.
     *
     * @param orderId The unique identifier of the order.
     * @param message The message to log.
     */
    void log(int orderId, String message);
}
