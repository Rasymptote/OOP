package ru.nsu.babich.server.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nsu.babich.server.domain.model.Field;
import ru.nsu.babich.server.domain.model.Food;
import ru.nsu.babich.server.domain.model.Player;

/**
 * Domain service that replenishes food up to a configured amount.
 */
@Service
public class FoodReplenishmentService {

    private final FoodGenerator foodGenerator;
    private final int targetFoodCount;

    /**
     * Constructs a replenishment service.
     *
     * @param foodGenerator Food generator used to spawn missing items.
     * @param targetFoodCount Desired number of food items kept on the field.
     */
    public FoodReplenishmentService(FoodGenerator foodGenerator,
                                    @Value("${game.food.replenish-count:3}") int targetFoodCount) {
        this.foodGenerator = Objects.requireNonNull(foodGenerator,
                "foodGenerator must not be null");
        if (targetFoodCount < 0) {
            throw new IllegalArgumentException("targetFoodCount must be non-negative");
        }
        this.targetFoodCount = targetFoodCount;
    }

    /**
     * Fills the board with food until the target amount is reached or no free cells remain.
     *
     * @param field Active field.
     * @param players Active players.
     * @param foods Current foods.
     * @return Updated foods list after replenishment.
     */
    public List<Food> replenish(Field field, List<Player> players, List<Food> foods) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");

        var result = new ArrayList<>(foods);
        while (result.size() < targetFoodCount) {
            var newFood = foodGenerator.generate(field, players, result);
            if (newFood.isEmpty()) {
                break;
            }
            result.add(newFood.get());
        }

        return List.copyOf(result);
    }
}

