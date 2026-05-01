package ru.nsu.babich.queue;

/**
 * A thread-safe buffer interface for storing and retrieving items.
 *
 * @param <T> the type of items stored in the buffer
 */
public interface BlockingQueue<T> {

    /**
     * Adds an item to the buffer, blocking if the buffer is full.
     *
     * @param item The item to add.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    void put(T item) throws InterruptedException;

    /**
     * Retrieves and removes an item from the buffer, blocking if the buffer is empty.
     *
     * @return The item retrieved from the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    T take() throws InterruptedException;
}
