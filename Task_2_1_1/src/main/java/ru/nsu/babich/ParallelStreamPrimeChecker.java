package ru.nsu.babich;

import java.util.Arrays;

/**
 * Represents a Prime checker implementation that uses Java parallel streams.
 */
public class ParallelStreamPrimeChecker implements PrimeChecker {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsComposite(int[] numbers) {
        return Arrays.stream(numbers)
                .parallel()
                .anyMatch(this::isComposite);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ParallelStreamPrimeChecker";
    }
}
