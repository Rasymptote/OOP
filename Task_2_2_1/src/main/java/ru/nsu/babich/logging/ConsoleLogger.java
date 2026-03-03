package ru.nsu.babich.logging;

/**
 * An implementation of the OrderLogger interface that logs messages to the console.
 */
public class ConsoleLogger implements OrderLogger {

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(int orderId, String message) {
        System.out.printf("[%d]: %s\n", orderId, message);
    }
}
