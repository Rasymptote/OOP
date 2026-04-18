package ru.nsu.babich.domain.service.strategy.foodtypepicking;

import ru.nsu.babich.domain.model.FoodType;

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
