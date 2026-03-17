package ru.nsu.babich.logging;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class OrderLoggerTest {

    protected OrderLogger logger;

    @ParameterizedTest
    @MethodSource
    void checkLog(int orderId, String message, String expectedOutput) {
    }

    static Stream<Arguments> checkLog() {
        return Stream.of(
                Arguments.of(1, "COOKING", "[1]: COOKING"),
                Arguments.of(2, "COOKED", "[2]: COOKED"),
                Arguments.of(3, "DELIVERING", "[3]: DELIVERING"),
                Arguments.of(4, "DELIVERED", "[4]: DELIVERED")
        );
    }
}