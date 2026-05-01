package ru.nsu.babich;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a Prime checker implementation that uses multiple threads.
 */
public class ThreadPrimeChecker implements PrimeChecker {

    private final int threadCount;


    /**
     * Constructs a ThreadPrimeChecker with the specified number of threads.
     *
     * @param threadCount The number of threads to use for checking composite numbers.
     */
    public ThreadPrimeChecker(int threadCount) {
        if (threadCount < 1) {
            throw new IllegalArgumentException("Thread count must be at least 1");
        }
        this.threadCount = threadCount;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsComposite(int[] numbers) {
        AtomicBoolean foundComposite = new AtomicBoolean(false);
        Thread[] threads = new Thread[threadCount];
        final int numsPerThread = numbers.length / threadCount;
        final int numsRemainder = numbers.length % threadCount;

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int startIndex = threadIndex * numsPerThread;
            int endIndex = startIndex + numsPerThread +
                    (threadIndex == threadCount - 1 ? numsRemainder : 0);

            threads[threadIndex] = new Thread(() -> {
                for (int i = startIndex; i < endIndex && !foundComposite.get(); i++) {
                    if (isComposite(numbers[i])) {
                        foundComposite.set(true);
                        break;
                    }
                }
            });
            threads[threadIndex].start();
        }

        for (Thread thread : threads) {
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return foundComposite.get();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ThreadPrimeChecker with " + threadCount + " threads";
    }
}
