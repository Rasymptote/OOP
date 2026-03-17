package ru.nsu.babich.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the OrderLogger interface that logs messages to the console.
 */
public class ConsoleLogger implements OrderLogger {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleLogger.class);
    /**
     * {@inheritDoc}
     */
    @Override
    public void log(int orderId, String message) {
        logger.info("[{}]: {}", orderId, message);
    }
}
