package ru.nsu.babich.worker;

import ru.nsu.babich.logging.OrderLogger;
import ru.nsu.babich.order.Order;
import ru.nsu.babich.queue.BoundedBlockingQueue;

/**
 * Represents a baker in the pizzeria who takes orders from the order queue,
 * cooks them, and places them in the storage for delivery.
 */
public class Baker implements PizzeriaWorker {

    private final int cookingSpeed;
    private final BoundedBlockingQueue<Order> orderQueue;
    private final BoundedBlockingQueue<Order> storage;
    private final OrderLogger orderLogger;

    /**
     * Constructs a Baker with the specified cooking speed, order queue, and storage.
     *
     * @param cookingSpeed The time in milliseconds it takes to cook an order.
     * @param orderQueue   The queue from which the baker takes orders to cook.
     * @param storage      The queue where the baker places cooked orders for delivery.
     * @param logger       The OrderLogger used to log the status of orders during cooking.
     */
    public Baker(int cookingSpeed, BoundedBlockingQueue<Order> orderQueue,
                 BoundedBlockingQueue<Order> storage, OrderLogger logger) {
        this.cookingSpeed = cookingSpeed;
        this.orderQueue = orderQueue;
        this.storage = storage;
        this.orderLogger = logger;
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
                orderLogger.log(order.id(), "COOKING");
                Thread.sleep(cookingSpeed);
                orderLogger.log(order.id(), "COOKED");
                storage.put(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            storage.stop();
        }
    }
}
