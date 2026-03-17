package ru.nsu.babich;

import java.util.List;
import java.util.stream.Stream;
import ru.nsu.babich.dto.PizzeriaSimulatorDto;
import ru.nsu.babich.logging.ConsoleLogger;
import ru.nsu.babich.logging.OrderLogger;
import ru.nsu.babich.order.Order;
import ru.nsu.babich.order.OrderGenerator;
import ru.nsu.babich.queue.BoundedBlockingQueue;
import ru.nsu.babich.worker.Baker;
import ru.nsu.babich.worker.Courier;

/**
 * Implements simulation logic for the pizzeria.
 */
public class PizzeriaSimulator implements Runnable {

    private final List<Runnable> workers;
    private final OrderGenerator orderGenerator;
    private final int workTime;

    /**
     * Constructs a PizzeriaSimulator with the specified bakers,
     * couriers, work time, and order generator.
     *
     * @param workers        A list of order generator, bakers,
     *                       couriers that will be executed in the simulation.
     * @param orderGenerator The OrderGenerator responsible for
     *                       generating orders during the simulation.
     * @param workTime       The total time (in milliseconds)
     *                       for which the simulation will run.
     */
    public PizzeriaSimulator(List<Runnable> workers, OrderGenerator orderGenerator, int workTime) {
        this.workers = workers;
        this.orderGenerator = orderGenerator;
        this.workTime = workTime;
    }

    /**
     * Creates a PizzeriaSimulator instance based on the provided
     * PizzeriaSimulatorDto configuration.
     *
     * @param pizzeriaSimulatorDto The configuration for the pizzeria
     *                             simulator, including work time,
     *                             storage capacity, bakers, and couriers.
     * @return A configured instance of PizzeriaSimulator ready
     * to run the simulation.
     */
    public static PizzeriaSimulator createPizzeriaSimulator(
            PizzeriaSimulatorDto pizzeriaSimulatorDto) {

        BoundedBlockingQueue<Order> orderQueue =
                new BoundedBlockingQueue<>(pizzeriaSimulatorDto.storageCapacity());
        BoundedBlockingQueue<Order> storage =
                new BoundedBlockingQueue<>(pizzeriaSimulatorDto.storageCapacity());
        OrderLogger logger = new ConsoleLogger();
        OrderGenerator orderGenerator = new OrderGenerator(orderQueue);

        List<Baker> bakers = pizzeriaSimulatorDto.bakers()
                .stream()
                .map(b -> new Baker(b.cookingSpeed(), orderQueue, storage, logger))
                .toList();

        List<Courier> couriers = pizzeriaSimulatorDto.couriers()
                .stream()
                .map(c -> new Courier(c.trunkCapacity(), c.deliverySpeed(), storage, logger))
                .toList();

        List<Runnable> workers = Stream.concat(
                Stream.of(orderGenerator),
                Stream.concat(bakers.stream(), couriers.stream())
        ).toList();

        return new PizzeriaSimulator(
                workers,
                orderGenerator,
                pizzeriaSimulatorDto.workTime()
        );
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