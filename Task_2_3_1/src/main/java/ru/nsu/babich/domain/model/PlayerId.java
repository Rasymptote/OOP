package ru.nsu.babich.domain.model;

import java.util.Objects;

/**
 * Immutable player identifier value object.
 *
 * @param id Identifier.
 */
public record PlayerId(String id) {
	/**
	 * Constructs a validated player identifier.
	 */
	public PlayerId {
		Objects.requireNonNull(id, "id must not be null");
		if (id.isBlank()) {
			throw new IllegalArgumentException("id must not be blank");
		}
	}
}
