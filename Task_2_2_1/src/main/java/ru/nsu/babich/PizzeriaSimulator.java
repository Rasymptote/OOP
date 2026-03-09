package ru.nsu.babich;

import java.util.List;
import ru.nsu.babich.order.OrderGenerator;

/**
 * Implements simulation logic for the pizzeria.
 */
public class PizzeriaSimulator implements Runnable {

    private final List<Runnable> workers;
    private final OrderGenerator orderGenerator;
    private final int workTime;

    /**
     * Constructs a PizzeriaSimulator with the specified bakers, couriers, work time, and order generator.
     *
     * @param workers        A list of order generator, bakers, couriers that will be executed in the simulation.
     * @param orderGenerator The OrderGenerator responsible for generating orders during the simulation.
     * @param workTime The total time (in milliseconds) for which the simulation will run.
     */
    public PizzeriaSimulator(List<Runnable> workers, OrderGenerator orderGenerator, int workTime) {
        this.workers = workers;
        this.orderGenerator = orderGenerator;
        this.workTime = workTime;
    }

    @Override
    public void run() {
        var threads = workers.stream()
                .map(worker -> {
                    Thread thread = new Thread(worker);
                    thread.start();
                    return thread;
                })
                .toList();

        try {
            Thread.sleep(workTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        orderGenerator.stop();

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}