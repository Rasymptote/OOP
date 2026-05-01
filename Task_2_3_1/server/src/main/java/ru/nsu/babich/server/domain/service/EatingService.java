package ru.nsu.babich.server.domain.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import ru.nsu.babich.server.domain.model.Food;
import ru.nsu.babich.server.domain.model.Player;
import ru.nsu.babich.server.domain.model.PlayerId;

/**
 * Domain service that applies food consumption effects.
 */
public class EatingService {

    /**
     * Handles food consumption for all alive players on this tick.
     *
     * @param players Current alive players.
     * @param foods Current food list.
     * @return Result of eating, containing updated player states and remaining foods.
     */
    public EatingResult handle(List<Player> players, List<Food> foods) {
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");

        Map<PlayerId, Integer> growthByPlayer = new HashMap<>();
        Set<Food> eatenFoods = new HashSet<>();

        for (Food food : foods) {
            findEater(players, food).ifPresent(eater -> {
                eatenFoods.add(food);
                growthByPlayer.merge(eater.id(), food.type().getGrowthTicks(), Integer::sum);
            });
        }

        List<Player> grownPlayers = players.stream()
                .map(player -> growPlayer(player, growthByPlayer.getOrDefault(player.id(), 0)))
                .toList();

        List<Food> remainingFoods = foods.stream().filter(food ->
                !eatenFoods.contains(food)).toList();

        return new EatingResult(grownPlayers, remainingFoods);
    }

    /**
     * Result of applying eating effects, containing updated player states and remaining foods.
     *
     * @param players Updated player states after applying growth and score increases.
     * @param foods Remaining foods that were not consumed this tick.
     */
    public record EatingResult(List<Player> players, List<Food> foods) {
    }

    private Player growPlayer(Player player, int ticksToAdd) {
        if (ticksToAdd <= 0) {
            return player;
        }
        return new Player(player.id(), player.movementStrategy(), player.snake().withAddedGrowthTicks(ticksToAdd),
                player.score() + ticksToAdd);
    }

    private Optional<Player> findEater(List<Player> players, Food food) {
        return players.stream()
                .filter(player -> CheckCollisionService.isSameCell(player.snake().getHead(), food.position()))
                .findFirst();
    }
}