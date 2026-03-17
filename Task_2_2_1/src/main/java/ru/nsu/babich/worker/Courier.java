package ru.nsu.babich.worker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.babich.order.Order;
import ru.nsu.babich.queue.BoundedBlockingQueue;

import static ru.nsu.babich.PizzeriaSimulator.LOG_TEMPLATE;

/**
 * Represents a courier in the pizzeria who takes cooked orders from the storage and delivers them.
 */
public class Courier implements PizzeriaWorker {
    private static final Logger logger = LoggerFactory.getLogger(Courier.class);

    private final int deliverySpeed;
    private final int trunkCapacity;
    private final BoundedBlockingQueue<Order> storage;
    private final List<Order> ordersToDeliver;

    /**
     * Constructs a Courier with the specified trunk capacity and storage.
     *
     * @param trunkCapacity The maximum number of orders the courier can carry at once.
     * @param deliverySpeed The time in milliseconds it takes to deliver an order.
     * @param storage       The queue from which the courier takes cooked orders for delivery.
     */
    public Courier(int trunkCapacity, int deliverySpeed,
                   BoundedBlockingQueue<Order> storage) {
        this.trunkCapacity = trunkCapacity;
        this.deliverySpeed = deliverySpeed;
        this.storage = storage;
        this.ordersToDeliver = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < trunkCapacity; i++) {
                    Order order = storage.take();
                    if (order == null) {
                        break;
                    }
                    ordersToDeliver.add(order);
                }

                if (ordersToDeliver.isEmpty()) {
                    break;
                }

                for (Order order : ordersToDeliver) {
                    logger.info(LOG_TEMPLATE, order.id(), "DELIVERING");
                    Thread.sleep(deliverySpeed);
                    logger.info(LOG_TEMPLATE, order.id(), "DELIVERED");
                }
                ordersToDeliver.clear();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
