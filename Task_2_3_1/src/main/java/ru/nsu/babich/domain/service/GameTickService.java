package ru.nsu.babich.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ru.nsu.babich.domain.model.Field;
import ru.nsu.babich.domain.model.Food;
import ru.nsu.babich.domain.model.GameState;
import ru.nsu.babich.domain.model.GameStatus;
import ru.nsu.babich.domain.model.Snake;

/**
 * Domain service that advances the game by one tick.
 */
public class GameTickService {

    private final MovementService movementService;
    private final EatingService eatingService;
    private final CheckCollisionService collisionService;

    /**
     * Constructs a game tick service.
     *
     * @param movementService  Service that computes snake movement.
     * @param eatingService    Service that applies eating effects.
     * @param collisionService Service that detects collisions.
     */
    public GameTickService(MovementService movementService, EatingService eatingService, CheckCollisionService collisionService) {
        this.movementService = Objects.requireNonNull(movementService, "movingService must not be null");
        this.eatingService = Objects.requireNonNull(eatingService, "eatingService must not be null");
        this.collisionService = Objects.requireNonNull(collisionService, "collisionService must not be null");
    }

    /**
     * Performs one simulation tick for the current game state.
     *
     * @param state Current game state.
     * @param field Active game field.
     * @return Next game state after movement, collision checks, and eating.
     */
    public GameState tick(GameState state, Field field) {
        Objects.requireNonNull(state, "state must not be null");
        Objects.requireNonNull(field, "field must not be null");

        if (!state.isRunning()) {
            return state;
        }

        Snake movedSnake = movementService.handle(state.snake());

        if (collisionService.hasCollision(movedSnake, field)) {
            return createState(movedSnake, state.foods(), GameStatus.LOST);
        }

        Optional<Food> eatenFood = findEatenFood(state.foods(), movedSnake);

        return eatenFood
                .map(food -> handleEating(state, field, movedSnake, food))
                .orElseGet(() -> createState(movedSnake, state.foods(), GameStatus.RUNNING));

    }

    private GameState handleEating(GameState state, Field field, Snake snake, Food eatenFood) {
        var result = eatingService.handle(field, snake, state.foods(), eatenFood);

        GameStatus status = result.isWin() ? GameStatus.WON : GameStatus.RUNNING;

        return createState(result.snake(), result.foods(), status);
    }

    private Optional<Food> findEatenFood(List<Food> foods, Snake snake) {
        return foods.stream()
                .filter(food -> collisionService.isSameCell(snake.getHead(), food.position()))
                .findFirst();
    }

    private GameState createState(Snake snake, List<Food> foods, GameStatus status) {
        return new GameState(snake, foods, status);
    }
}