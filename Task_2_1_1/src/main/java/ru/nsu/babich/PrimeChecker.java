package ru.nsu.babich;

/**
 * Interface for checking prime and composite numbers within arrays.
 */
public interface PrimeChecker {

    /**
     * Checks whether the array contains at least one composite number.
     * @param numbers The array of integers to check.
     * @return {@code true} if there is at least one composite number, {@code false} otherwise.
     */
    boolean containsComposite(int[] numbers);


    /**
     * Determines if a number is composite.
     * @param number The number to check.
     * @return {@code true} if the number is composite, {@code false} otherwise.
     */
    default boolean isComposite(int number) {
        if (number <= 1) {
            return true;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
}
