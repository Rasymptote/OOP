package ru.nsu.babich.server.domain.model;

import java.util.List;
import java.util.Objects;

/**
 * Immutable snapshot of the current game state.
 *
 * @param field Active game field dimensions.
 * @param players List of active players in the game.
 * @param foods List of active food items on the board.
 */
public record GameState(Field field, List<Player> players, List<Food> foods) {
    /**
     * Validates game state components and creates defensive copies of mutable lists.
     *
     * @param field Active game field dimensions.
     * @param players List of active players in the game.
     * @param foods List of active food items on the board.
     */
    public GameState {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");
        players = List.copyOf(players);
        foods = List.copyOf(foods);
    }
}
