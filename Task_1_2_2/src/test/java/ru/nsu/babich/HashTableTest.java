package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Stream;

class HashTableTest {
    HashTable<String, Number> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @ParameterizedTest
    @MethodSource
    void checkPut(String message, List<HashTable.Entry<String, Number>> entries) {
        assertEquals(
                0, table.size(),
                message + ". An empty table should have a zero size"
        );

        for (var entry : entries) {
            assertDoesNotThrow(
                    () -> table.put(entry.getKey(), entry.getValue()),
                    message + ". Putting an entry shouldn't cause any exception"
            );
            assertTrue(
                    table.containsKey(entry.getKey()),
                    message + ". Table should contain an entry after insertion"
            );
        }

        for (var entry : entries) {
            assertTrue(
                    table.containsKey(entry.getKey()),
                    message + ". A table should contain all entries after adding"
            );
        }
    }

    static Stream<Arguments> checkPut() {
        return Stream.of(
                Arguments.of("Single element",
                        List.of(new HashTable.Entry<>("one", 1))),

                Arguments.of("Multiple elements",
                        List.of(
                                new HashTable.Entry<>("one", 1),
                                new HashTable.Entry<>("two", 2),
                                new HashTable.Entry<>("three", 3)
                        )),

                Arguments.of("Null key",
                        List.of(
                                new HashTable.Entry<>("null", 0)
                        )),

                Arguments.of("Null value",
                        List.of(
                        new HashTable.Entry<>("key", null)
                        )),

                Arguments.of("Update value",
                        List.of(
                                new HashTable.Entry<>("one", 1),
                                new HashTable.Entry<>("two", 2),
                                new HashTable.Entry<>("one", 0)
                        )));
    }

    @ParameterizedTest
    @MethodSource
    void checkRemove(String message, List<HashTable.Entry<String, Number>> entries, String keyToRemove) {
        for (var entry : entries) {
            table.put(entry.getKey(), entry.getValue());
        }
        table.remove(keyToRemove);
        assertFalse(
                table.containsKey(keyToRemove),
                message + ". Key should not exist after removal"
        );
    }

    static Stream<Arguments> checkRemove() {
        return Stream.of(
                Arguments.of("Single element",
                        List.of(new HashTable.Entry<>("one", 1)), "one"),

                Arguments.of("Non-present element",
                        List.of(
                                new HashTable.Entry<>("one", 1),
                                new HashTable.Entry<>("two", 2),
                                new HashTable.Entry<>("three", 3)
                        ),
                        "four"
                ),

                Arguments.of("Null key",
                        List.of(
                                new HashTable.Entry<>("null", 0)
                        ),
                        "null"
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void checkEquals(String message, List<HashTable.Entry<String, Number>> entries1, List<HashTable.Entry<String, Number>> entries2,
                     boolean expectedEquals) {
        HashTable<String, Number> table1 = new HashTable<>();
        HashTable<String, Number> table2 = new HashTable<>();
        for (var entry : entries1) {
            table1.put(entry.getKey(), entry.getValue());
        }
        for (var entry : entries2) {
            table2.put(entry.getKey(), entry.getValue());
        }
        assertEquals(
                expectedEquals, table1.equals(table2),
                message + ". table1 should be equal to table2"
        );
        assertEquals(
                expectedEquals, table2.equals(table1),
                message + ". table2 should be equal to table1"
        );
    }

    static Stream<Arguments> checkEquals() {
        return Stream.of(
                Arguments.of("Same values",
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 2)),
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 2)),
                        true
                ),
                Arguments.of("Different values",
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 2)),
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 3)),
                        false
                ),
                Arguments.of("Different size",
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 2)),
                        List.of(new HashTable.Entry<>("a", 1)),
                        false
                ),
                Arguments.of("Different keys",
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("b", 2)),
                        Arrays.asList(new HashTable.Entry<>("a", 1), new HashTable.Entry<>("c", 2)),
                        false
                )
        );
    }

    @Test
    void checkToString() {
        assertEquals(
                "{}", table.toString(),
                "Empty table should be represented as {}"
        );
        table.put("a", 1);
        table.put("b", 2);
        String result = table.toString();
        assertTrue(result.contains("a=1"));
        assertTrue(result.contains("b=2"));
    }

    @Test
    void checkConcurrentModification() {
        table.put("a", 1);
        table.put("b", 2);
        assertThrows(ConcurrentModificationException.class, () -> {
            for (var entry : table) {
                table.put("c", 3);
            }
        });
    }
}
