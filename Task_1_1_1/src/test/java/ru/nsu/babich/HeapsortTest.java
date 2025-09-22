package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class HeapsortTest {

    @Test
    void checkExampleArray() {
        var array = new int[]{5, 4, 3, 2, 1};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void checkEmptyArray() {
        var array = new int[]{};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void checkDuplicates() {
        var array = new int[]{8, 8, 8, 3, 67, 2, 2, 1, 7};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 2, 3, 7, 8, 8, 8, 67}, array);
    }

    @Test
    void checkSingleElement() {
        var array = new int[]{0};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{0}, array);
    }

    @Test
    void checkAlreadySorted() {
        var array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, array);
    }

    @Test
    void checkReverseSorted() {
        var array = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Heapsort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, array);
    }
}
