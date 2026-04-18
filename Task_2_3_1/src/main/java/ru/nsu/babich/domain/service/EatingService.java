package ru.nsu.babich.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.nsu.babich.domain.model.Field;
import ru.nsu.babich.domain.model.Food;
import ru.nsu.babich.domain.model.Snake;

/**
 * Domain service that applies food consumption effects.
 */
public class EatingService {

    private final FoodGenerator foodGenerator;

    /**
     * Constructs an eating service.
     *
     * @param foodGenerator Service that replenishes food after eating.
     */
    public EatingService(FoodGenerator foodGenerator) {
        this.foodGenerator = Objects.requireNonNull(foodGenerator, "foodGenerator must not be null");
    }

    /**
     * Handles snake interaction with an eaten food item.
     *
     * @param field     Active game field.
     * @param snake     Current snake state.
     * @param foods     Current food list.
     * @param eatenFood Food consumed on this tick.
     * @return Updated state after growth, food removal, and replenishment.
     */
    public EatingResult handle(Field field, Snake snake, List<Food> foods, Food eatenFood) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(snake, "snake must not be null");
        Objects.requireNonNull(foods, "foods must not be null");
        Objects.requireNonNull(eatenFood, "eatenFood must not be null");

        Snake grownSnake = growSnake(snake, eatenFood);

        List<Food> remainingFoods = removeEatenFood(foods, eatenFood);

        List<Food> replenishedFoods = replenish(field, grownSnake, remainingFoods, foods.size());

        boolean isWin = replenishedFoods.isEmpty();

        return new EatingResult(grownSnake, replenishedFoods, isWin);
    }

    /**
     * Result of a food consumption step.
     *
     * @param snake Updated snake state.
     * @param foods Updated food list.
     * @param isWin {@code true} when no food can remain on the field.
     */
    public record EatingResult(Snake snake, List<Food> foods, boolean isWin) {
    }

    private Snake growSnake(Snake snake, Food eatenFood) {
        return snake.withAddedGrowthTicks(eatenFood.type().getGrowthTicks());
    }

    private List<Food> removeEatenFood(List<Food> foods, Food eatenFood) {
        return foods.stream().filter(food -> !food.equals(eatenFood)).toList();
    }

    private List<Food> replenish(Field field, Snake snake, List<Food> foods, int targetFoodCount) {
        var result = new ArrayList<>(foods);

        while (result.size() < targetFoodCount) {
            var newFood = foodGenerator.generate(field, snake, result);
            if (newFood.isEmpty()) {
                break;
            }
            result.add(newFood.get());
        }

        return List.copyOf(result);
    }
}