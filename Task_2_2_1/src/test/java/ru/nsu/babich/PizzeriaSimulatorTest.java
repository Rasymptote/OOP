package ru.nsu.babich;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.babich.order.Order;
import ru.nsu.babich.order.OrderGenerator;
import ru.nsu.babich.queue.BoundedBlockingQueue;
import ru.nsu.babich.worker.Baker;
import ru.nsu.babich.worker.Courier;

class PizzeriaSimulatorTest {

    @Test
    void checkOrdersAreProcessedAfterClosing() {
        var orderQueue = new BoundedBlockingQueue<Order>(8);
        var storage = new BoundedBlockingQueue<Order>(8);
        var generator = new OrderGenerator(orderQueue);
        PizzeriaSimulator simulator = new PizzeriaSimulator(
                List.of(generator,
                        new Baker(1, orderQueue,
                                storage, new ConsoleLogger()),
                        new Courier(1, 1,
                                storage, new ConsoleLogger())
                ),
                generator,
                1000
        );

        var thread = new Thread(simulator);
        thread.start();
        assertTrue(orderQueue.isEmpty());
        assertTrue(storage.isEmpty());
    }
}