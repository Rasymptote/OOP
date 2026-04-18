package ru.nsu.babich.domain.model;

import java.util.List;
import java.util.Objects;

/**
 * Immutable snapshot of the current game state.
 *
 * @param field Active game field dimensions.
 * @param players List of active players in the game.
 * @param foods List of active food items on the board.
 * @param status Current game lifecycle status.
 */
public record GameState(Field field, List<Player> players, List<Food> foods, GameStatus status) {
    public GameState {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");
        Objects.requireNonNull(status, "status must not be null");
        players = List.copyOf(players);
        foods = List.copyOf(foods);
    }

    /**
     * Checks whether the game is still in progress.
     *
     * @return {@code true} when status is {@link GameStatus#RUNNING}
     */
    public boolean isRunning() {
        return status == GameStatus.RUNNING;
    }
}
