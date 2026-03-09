package ru.nsu.babich.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A thread-safe bounded blocking queue implementation of the Buffer interface.
 *
 * @param <T> The type of items stored in the buffer.
 */
public class BoundedBlockingQueue<T> implements BlockingQueue<T> {
    private final Queue<T> queue;
    private final int capacity;
    private int activeProducers;

    /**
     * Constructs a BoundedBlockingQueue with the specified capacity.
     *
     * @param capacity The capacity of the queue.
     */
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.activeProducers = 0;
    }

    /**
     * Indicates that a producer has started producing items.
     * This method should be called by producers before they start putting items into the queue.
     */
    public synchronized void start() {
        activeProducers++;
    }

    /**
     * Indicates that a producer has finished producing items.
     * This method should be called by producers after they have finished putting items into the queue.
     */
    public synchronized void stop() {
        activeProducers--;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(item);
        notifyAll();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty() && activeProducers != 0) {
            wait();
        }

        T item = queue.poll();
        notifyAll();
        return item;
    }
}
