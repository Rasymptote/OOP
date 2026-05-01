package ru.nsu.babich.order;

import ru.nsu.babich.queue.BoundedBlockingQueue;

/**
 * Implements orders generation. Generates orders with unique id and puts them in the order queue.
 */
public class OrderGenerator implements Runnable {

    private final BoundedBlockingQueue<Order> orderQueue;
    private int id;
    private boolean running;

    /**
     * Constructs an OrderGenerator with the specified order queue.
     *
     * @param orderQueue The queue where generated orders will be placed.
     */
    public OrderGenerator(BoundedBlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
        this.id = 0;
        this.running = true;
    }


    /**
     * Stops the order generation process.
     */
    public void stop() {
        this.running = false;
    }


    @Override
    public void run() {
        orderQueue.start();
        try {
            while (running) {
                Order order = new Order(id++);
                orderQueue.put(order);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            orderQueue.stop();
        }
    }
}
