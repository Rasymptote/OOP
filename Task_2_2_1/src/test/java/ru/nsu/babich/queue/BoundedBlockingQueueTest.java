package ru.nsu.babich.queue;

import org.junit.jupiter.api.BeforeEach;

class BoundedBlockingQueueTest extends BlockingQueueTest {

    @BeforeEach
    void setUp() {
        queue = new BoundedBlockingQueue<>(8);
    }
}