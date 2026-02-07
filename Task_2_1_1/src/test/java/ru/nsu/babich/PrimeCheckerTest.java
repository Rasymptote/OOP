package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class PrimeCheckerTest {

    protected PrimeChecker primeChecker;


    @ParameterizedTest
    @MethodSource
    void checkContainsComposite(int[] numbers, boolean expected) {
        assertEquals(expected, primeChecker.containsComposite(numbers));
    }


    static Stream<Arguments> checkContainsComposite() {
        return Stream.of(
                Arguments.of(new int[]{2, 3, 5, 7, 11}, false),
                Arguments.of(new int[]{4, 6, 8, 9, 10}, true),
                Arguments.of(new int[]{13, 17, 19, 23}, false),
                Arguments.of(new int[]{12, 15, 18, 20}, true),
                Arguments.of(new int[]{29, 31, 37, 41}, false),
                Arguments.of(new int[]{14, 21, 25, 28}, true),
                Arguments.of(new int[]{2}, false),
                Arguments.of(new int[]{1}, true),
                Arguments.of(new int[]{0}, true),
                Arguments.of(new int[]{1000000007, 1000000009, 1000000033,
                    1000000087, 1000000093, 1000000097}, false),
                Arguments.of(new int[]{1000000123, 1000000181, 1000000207,
                    1000000223, 1000000241, 1000000272, 1000000289}, true)
        );
    }
}