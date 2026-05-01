package ru.nsu.babich;

import org.junit.jupiter.api.BeforeEach;

class SequentialPrimeCheckerTest extends PrimeCheckerTest {

    @BeforeEach
    void setUp() {
        primeChecker = new SequentialPrimeChecker();
    }
}