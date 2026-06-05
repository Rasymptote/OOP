package ru.nsu.babich.client.presentation.view.model.renderable;

import javafx.scene.paint.Color;
import ru.nsu.babich.client.dsl.ColorPalette;

/**
 * Stores colors used for rendering objects.
 */
public class RenderablePalette {
    private RenderablePalette() {
    }

    public static Color SNAKE = Color.web("#22c55e");

    public static Color FOOD_NORMAL = Color.web("#ff5c7a");
    public static Color FOOD_BONUS = Color.web("#ffd166");
    public static Color FOOD_POISON = Color.web("#a855f7");

    public static Color NEUTRAL = Color.web("#94a3b8");

    public static void apply(ColorPalette palette) {
        if (palette == null) {
            return;
        }
        if (palette.snake() != null) {
            SNAKE = Color.web(palette.snake());
        }
        if (palette.foodNormal() != null) {
            FOOD_NORMAL = Color.web(palette.foodNormal());
        }
        if (palette.foodBonus() != null) {
            FOOD_BONUS = Color.web(palette.foodBonus());
        }
        if (palette.foodPoison() != null) {
            FOOD_POISON = Color.web(palette.foodPoison());
        }
    }
}
