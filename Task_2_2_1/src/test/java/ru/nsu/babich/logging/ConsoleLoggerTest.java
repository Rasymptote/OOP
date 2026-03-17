package ru.nsu.babich.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.LoggerFactory;

class ConsoleLoggerTest extends OrderLoggerTest {

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        logger = new ConsoleLogger();
        Logger consoleLogger = (Logger) LoggerFactory.getLogger(ConsoleLogger.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        consoleLogger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
    }

    @ParameterizedTest
    @MethodSource
    void checkLog(int orderId, String message, String expectedOutput) {
        logger.log(orderId, message);
        var logs = listAppender.list;
        var actualOutput = logs.get(0).getFormattedMessage();
        assertEquals(expectedOutput, actualOutput);
    }
}