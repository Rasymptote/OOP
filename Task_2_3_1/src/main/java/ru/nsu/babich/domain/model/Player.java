package ru.nsu.babich.domain.model;

import java.util.Objects;
import ru.nsu.babich.domain.service.strategy.MovementStrategy;

/**
 * Immutable player containing identity and snake state.
 *
 * @param id Player identifier.
 * @param snake Current snake state for the player.
 */
public record Player(PlayerId id, MovementStrategy movementStrategy, Snake snake, int score) {
	/**
	 * Constructs a validated player value.
	 */
	public Player {
		Objects.requireNonNull(id, "id must not be null");
		Objects.requireNonNull(movementStrategy, "movementStrategy must not be null");
		Objects.requireNonNull(snake, "snake must not be null");
	}
}
