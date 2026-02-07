package ru.nsu.babich;

import org.junit.jupiter.api.BeforeEach;

class ThreadPrimeCheckerTest extends PrimeCheckerTest {

    @BeforeEach
    void setUp() {
        primeChecker = new ThreadPrimeChecker(4);
    }
}