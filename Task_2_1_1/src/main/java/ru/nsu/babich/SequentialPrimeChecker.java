package ru.nsu.babich;

/**
 * Represents a Prime checker implementation that checks numbers sequentially.
 */
public class SequentialPrimeChecker implements PrimeChecker {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsComposite(int[] numbers) {
        for (int number : numbers) {
            if (isComposite(number)) {
                return true;
            }
        }
        return false;
    }
}
