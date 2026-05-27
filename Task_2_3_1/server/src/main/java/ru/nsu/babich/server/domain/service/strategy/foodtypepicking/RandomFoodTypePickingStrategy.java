package ru.nsu.babich.server.domain.service.strategy.foodtypepicking;

import java.util.Objects;
import java.util.Random;
import ru.nsu.babich.server.domain.model.FoodType;

/**
 * Food type strategy that picks a random value.
 */
public class RandomFoodTypePickingStrategy implements FoodTypePickingStrategy {

    private final Random random;

    /**
     * Constructs a random food type strategy.
     *
     * @param random Random source used for selection.
     */
    public RandomFoodTypePickingStrategy(Random random) {
        this.random = Objects.requireNonNull(random, "random must not be null");
    }

    /**
     * Selects a random food type.
     *
     * @return Randomly selected food type.
     */
    @Override
    public FoodType pick() {
        FoodType[] values = FoodType.values();
        return values[random.nextInt(values.length)];
    }
}
