package ru.nsu.babich.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ru.nsu.babich.domain.model.Field;
import ru.nsu.babich.domain.model.Food;
import ru.nsu.babich.domain.model.FoodType;
import ru.nsu.babich.domain.model.Player;
import ru.nsu.babich.domain.model.Point;
import ru.nsu.babich.domain.service.strategy.cellpicking.CellPickingStrategy;
import ru.nsu.babich.domain.service.strategy.foodtypepicking.FoodTypePickingStrategy;

/**
 * Generates food items for the current game state.
 */
public class FoodGenerator {

    private final CellPickingStrategy cellPickingStrategy;
    private final FoodTypePickingStrategy foodTypePickingStrategy;
    private final FreeCellsService freeCellsService;

    /**
     * Constructs a food generator.
     *
     * @param cellPickingStrategy Strategy that selects a free cell for new food.
     * @param foodTypePickingStrategy Strategy that selects the type of new food.
     * @param freeCellsService Service that computes available cells.
     */
    public FoodGenerator(CellPickingStrategy cellPickingStrategy,
                         FoodTypePickingStrategy foodTypePickingStrategy,
                         FreeCellsService freeCellsService) {
        this.cellPickingStrategy = Objects.requireNonNull(cellPickingStrategy, "cellPickingStrategy must not be null");
        this.foodTypePickingStrategy = Objects.requireNonNull(foodTypePickingStrategy, "foodTypePickingStrategy must not be null");
        this.freeCellsService = Objects.requireNonNull(freeCellsService, "freeCellsService must not be null");
    }

    /**
     * Tries to generate one food item on a free cell.
     *
     * @param field Game field used to compute free cells.
     * @param players All active players whose snakes occupy field cells.
     * @param foods Current foods already placed on the field.
     * @return Generated food, or empty when generation is impossible.
     */
    public Optional<Food> generate(Field field, List<Player> players, List<Food> foods) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");

        List<Point> freeCells = freeCellsService.getFreeCells(field, players, foods);
        if (freeCells.isEmpty()) {
            return Optional.empty();
        }
        Optional<Point> point = cellPickingStrategy.pick(freeCells);
        if (point.isEmpty()) {
            return Optional.empty();
        }
        FoodType type = foodTypePickingStrategy.pick();

        return Optional.of(new Food(point.get(), type));
    }
}