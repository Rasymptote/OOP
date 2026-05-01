package ru.nsu.babich.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class BlockingQueueTest {

    protected BlockingQueue<Object> queue;

    @ParameterizedTest
    @MethodSource
    void checkPutAndTake(List<Object> itemsToPut, int itemsToTake,
                         List<Object> expectedTakenItems) {
        for (Object item : itemsToPut) {
            try {
                queue.put(item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 0; i < itemsToTake; i++) {
            try {
                Object takenItem = queue.take();
                assertEquals(expectedTakenItems.get(i), takenItem);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static Stream<Arguments> checkPutAndTake() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3),
                        2,
                        List.of(1, 2)
                ),

                Arguments.of(
                        List.of("a", "b", "c", "d"),
                        4,
                        List.of("a", "b", "c", "d")
                ),

                Arguments.of(
                        List.of(true, false),
                        1,
                        List.of(true)
                ),

                Arguments.of(
                        List.of(10),
                        1,
                        List.of(10)
                ),

                Arguments.of(
                        List.of(),
                        0,
                        List.of()
                )
        );
    }
}