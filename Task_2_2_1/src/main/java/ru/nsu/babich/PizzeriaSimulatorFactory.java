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
 * Factory class for creating instances of PizzeriaSimulator based on the provided configuration.
 */
public class PizzeriaSimulatorFactory {

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
}
