package ru.nsu.babich;

import java.util.Arrays;

/**
 * Main class.
 */
public class Main {

    /**
     * Program entry point.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int[] numbers = {5, 4, 3, 2, 1};
        Heapsort.heapsort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
