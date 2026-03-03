package ru.nsu.babich.buffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A thread-safe bounded blocking queue implementation of the Buffer interface.
 *
 * @param <T> The type of items stored in the buffer.
 */
public class BoundedBlockingQueue<T> implements Buffer<T> {
    private final Queue<T> queue;
    private final int capacity;

    /**
     * Constructs a BoundedBlockingQueue with the specified capacity.
     *
     * @param capacity The capacity of the queue.
     */
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }


    @Override
    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }


    @Override
    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }
}
