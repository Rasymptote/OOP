package ru.nsu.babich.client.presentation.view.model.renderable;

import javafx.scene.paint.Color;

/**
 * Stores colors used for rendering objects.
 */
public class RenderablePalette {
    private RenderablePalette() {
    }

    public static final Color SNAKE = Color.web("#22c55e");

    public static final Color FOOD_NORMAL = Color.web("#ff5c7a");
    public static final Color FOOD_BONUS = Color.web("#ffd166");
    public static final Color FOOD_POISON = Color.web("#a855f7");

    public static final Color NEUTRAL = Color.web("#94a3b8");
}
