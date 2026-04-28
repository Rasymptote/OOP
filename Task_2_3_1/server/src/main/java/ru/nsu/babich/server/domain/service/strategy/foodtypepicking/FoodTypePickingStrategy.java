package ru.nsu.babich.server.domain.service.strategy.foodtypepicking;

import ru.nsu.babich.server.domain.model.FoodType;

/**
 * Strategy for selecting a type of generated food.
 */
public interface FoodTypePickingStrategy {

	/**
	 * Selects a food type for the next generated item.
	 *
	 * @return Selected food type.
	 */
	FoodType pick();
}
