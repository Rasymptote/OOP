package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SearcherTest {

    @ParameterizedTest
    @MethodSource
    void checkFind(String message, String content, String word, List<Long> result) {
        try (var writer = new BufferedWriter(new FileWriter("test.txt"))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(
                result, Searcher.find("test.txt", word),
                message
        );
    }

    static Stream<Arguments> checkFind() {
        return Stream.of(
                Arguments.of("Empty content", "", "Hello", List.of()),
                Arguments.of("No occurrences", "Hello World", "world", List.of()),
                Arguments.of("One occurrence", "Hello World", "World", List.of(6L)),
                Arguments.of("Multiple occurrences", "Hello World", "o", List.of(4L, 7L)),
                Arguments.of("Collisions", "abababa", "aba", List.of(0L, 2L, 4L)),
                Arguments.of("Encoding", "你好世界", "世界", List.of(2L))
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkLargeFind(String message, String word, Integer occurrenceNumber, Long minLength) {
        var result = generateLargeFile(word, occurrenceNumber, minLength);
        assertEquals(
                result, Searcher.find("test.txt", word),
                message
        );
    }

    static Stream<Arguments> checkLargeFind() {
        return Stream.of(
                Arguments.of("Very much occurrences", "Hello World", 1_000_000, 0L),
                Arguments.of("Very large file", "Hello", 10, 1_000_000_000L)
        );
    }

    static List<Long> generateLargeFile(String word, Integer occurrenceNumber, Long minLength) {
        List<Long> result = new ArrayList<>();
        var pos = 0L;
        var random = new Random();
        try (var writer = new BufferedWriter(new FileWriter("test.txt"))) {
            while (occurrenceNumber > 0) {
                if (random.nextDouble() >= 0.85) {
                    result.add(pos);
                    writer.write(word);
                    pos += word.length();
                    occurrenceNumber--;
                } else {
                    writer.write(random.nextInt(32, 126));
                    pos++;
                }
            }
            while (pos < minLength) {
                writer.write(random.nextInt(32, 126));
                pos++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
