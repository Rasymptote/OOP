package ru.nsu.babich.server.domain.model;

/**
 * Types of food that can be consumed by the snake.
 */
public enum FoodType {
    NORMAL(1),
    BONUS(2),
    POISON(0);

    private final int growthTicks;

    FoodType(int growthTicks) {
        this.growthTicks = growthTicks;
    }

    /**
     * Returns how many pending growth ticks are added after consuming this food.
     *
     * @return Growth ticks associated with this food type.
     */
    public int getGrowthTicks() {
        return growthTicks;
    }
}

