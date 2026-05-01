package ru.nsu.babich;

import org.junit.jupiter.api.BeforeEach;

class ParallelStreamPrimeCheckerTest extends PrimeCheckerTest {

    @BeforeEach
    void setUp() {
        primeChecker = new ParallelStreamPrimeChecker();
    }
}