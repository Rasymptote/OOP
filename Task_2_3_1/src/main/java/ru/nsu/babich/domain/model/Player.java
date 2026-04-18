package ru.nsu.babich.domain.model;

import java.util.Objects;

/**
 * Immutable player containing identity and snake state.
 *
 * @param id Player identifier.
 * @param snake Current snake state for the player.
 */
public record Player(PlayerId id, Snake snake) {
	/**
	 * Constructs a validated player value.
	 */
	public Player {
		Objects.requireNonNull(id, "id must not be null");
		Objects.requireNonNull(snake, "snake must not be null");
	}
}
