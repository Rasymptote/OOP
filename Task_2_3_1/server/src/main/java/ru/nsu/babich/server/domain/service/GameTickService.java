package ru.nsu.babich.server.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import ru.nsu.babich.server.domain.model.Field;
import ru.nsu.babich.server.domain.model.Food;
import ru.nsu.babich.server.domain.model.GameState;
import ru.nsu.babich.server.domain.model.Player;

/**
 * Domain service that advances the game by one tick.
 */
@Service
public class GameTickService {

    private final MovementService movementService;
    private final EatingService eatingService;
    private final FoodReplenishmentService foodReplenishmentService;

    /**
     * Constructs a game tick service.
     *
     * @param movementService  Service that computes snake movement.
     * @param eatingService    Service that applies eating effects.
     * @param foodReplenishmentService Service that replenishes missing food.
     */
    public GameTickService(MovementService movementService,
                           EatingService eatingService,
                           FoodReplenishmentService foodReplenishmentService) {
        this.movementService = Objects.requireNonNull(movementService,
                "movingService must not be null");
        this.eatingService = Objects.requireNonNull(eatingService,
                "eatingService must not be null");
        this.foodReplenishmentService = Objects.requireNonNull(foodReplenishmentService,
                "foodReplenishmentService must not be null");
    }

    /**
     * Performs one simulation tick for the current game state.
     *
     * @param state Current game state.
     * @return Next game state after movement, collision checks, and eating.
     */
    public GameState tick(GameState state) {
        Objects.requireNonNull(state, "state must not be null");
        var field = state.field();

        var movedPlayers = movePlayers(state.players());
        var alivePlayers = filterAlivePlayers(movedPlayers, field);

        if (alivePlayers.isEmpty()) {
            return createState(field, List.of(), state.foods());
        }

        return applyEatingAndReplenishment(field, alivePlayers, state.foods());
    }

    private List<Player> movePlayers(List<Player> players) {
        return players.stream()
                .map(player -> new Player(
                        player.id(),
                        player.movementStrategy(),
                        movementService.handle(player.snake(), player.movementStrategy()),
                        player.score()
                ))
                .toList();
    }

    private List<Player> filterAlivePlayers(List<Player> movedPlayers, Field field) {
        var collidedPlayerIds = CheckCollisionService.findCollidedPlayerIds(movedPlayers, field);
        return movedPlayers.stream()
                .filter(player -> !collidedPlayerIds.contains(player.id()))
                .toList();
    }

    private GameState applyEatingAndReplenishment(Field field, List<Player> alivePlayers,
                                                  List<Food> foods) {
        var eatingResult = eatingService.handle(alivePlayers, foods);
        List<Food> replenishedFoods = foodReplenishmentService.replenish(
                field,
                eatingResult.players(),
                eatingResult.foods()
        );
        return createState(field, eatingResult.players(), replenishedFoods);
    }

    private GameState createState(Field field, List<Player> players, List<Food> foods) {
        return new GameState(field, players, foods);
    }
}