package ru.nsu.babich.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.babich.order.Order;
import ru.nsu.babich.queue.BoundedBlockingQueue;

import static ru.nsu.babich.PizzeriaSimulator.LOG_TEMPLATE;

/**
 * Represents a baker in the pizzeria who takes orders from the order queue,
 * cooks them, and places them in the storage for delivery.
 */
public class Baker implements PizzeriaWorker {
    private static final Logger logger = LoggerFactory.getLogger(Baker.class);

    private final int cookingSpeed;
    private final BoundedBlockingQueue<Order> orderQueue;
    private final BoundedBlockingQueue<Order> storage;

    /**
     * Constructs a Baker with the specified cooking speed, order queue, and storage.
     *
     * @param cookingSpeed The time in milliseconds it takes to cook an order.
     * @param orderQueue   The queue from which the baker takes orders to cook.
     * @param storage      The queue where the baker places cooked orders for delivery.
     */
    public Baker(int cookingSpeed, BoundedBlockingQueue<Order> orderQueue,
                 BoundedBlockingQueue<Order> storage) {
        this.cookingSpeed = cookingSpeed;
        this.orderQueue = orderQueue;
        this.storage = storage;
    }


    @Override
    public void run() {
        storage.start();
        try {
            while (true) {
                Order order = orderQueue.take();
                if (order == null) {
                    break;
                }
                logger.info(LOG_TEMPLATE, order.id(), "COOKING");
                Thread.sleep(cookingSpeed);
                logger.info(LOG_TEMPLATE, order.id(), "COOKED");
                storage.put(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            storage.stop();
        }
    }
}
