package ru.nsu.babich.worker;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.babich.queue.BoundedBlockingQueue;
import ru.nsu.babich.logging.OrderLogger;
import ru.nsu.babich.order.Order;

/**
 * Represents a courier in the pizzeria who takes cooked orders from the storage and delivers them.
 */
public class Courier extends PizzeriaWorker {

    private final int deliverySpeed;
    private final int trunkCapacity;
    private final BoundedBlockingQueue<Order> storage;
    private final List<Order> ordersToDeliver;
    private final OrderLogger orderLogger;

    /**
     * Constructs a Courier with the specified trunk capacity and storage.
     *
     * @param trunkCapacity The maximum number of orders the courier can carry at once.
     * @param storage       The queue from which the courier takes cooked orders for delivery.
     */
    public Courier(int trunkCapacity, int deliverySpeed,
                   BoundedBlockingQueue<Order> storage, OrderLogger logger) {
        this.trunkCapacity = trunkCapacity;
        this.deliverySpeed = deliverySpeed;
        this.storage = storage;
        this.orderLogger = logger;
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
                    orderLogger.log(order.id(), "DELIVERING");
                    Thread.sleep(deliverySpeed);
                    orderLogger.log(order.id(), "DELIVERED");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
