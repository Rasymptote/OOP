package ru.nsu.babich.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import ru.nsu.babich.queue.BoundedBlockingQueue;

class OrderGeneratorTest {

    @Test
    void checkGeneratingOrders() throws InterruptedException {
        BoundedBlockingQueue<Order> orderQueue = new BoundedBlockingQueue<>(10);
        var generator = new OrderGenerator(orderQueue);
        var thread = new Thread(generator);
        thread.start();
        Thread.sleep(1000);
        generator.stop();
        thread.join();
        assertFalse(orderQueue.isEmpty());

        var previousId = orderQueue.take().id();
        while (!orderQueue.isEmpty()) {
            var id = orderQueue.take().id();
            assertEquals(1, id - previousId);
            previousId = id;
        }
    }
}