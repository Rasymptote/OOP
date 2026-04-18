package ru.nsu.babich.presentation.view.model.renderable;

import java.util.List;
import javafx.scene.paint.Color;
import ru.nsu.babich.domain.model.Food;
import ru.nsu.babich.domain.model.FoodType;
import ru.nsu.babich.presentation.view.model.figure.Figure;
import ru.nsu.babich.presentation.view.model.figure.FigureType;

/**
 * Adapts food entity for rendering.
 *
 * @param food Food entity.
 */
public record FoodRenderable(Food food) implements Renderable {

    /** {@inheritDoc} */
    @Override
    public List<Figure> toFigures() {
        return List.of(new Figure(FigureType.CIRCLE, food.position(), 1, resolveColor(food.type())));
    }

    private Color resolveColor(FoodType type) {
        return switch (type) {
            case NORMAL -> Color.GREENYELLOW;
            case BONUS -> Color.GOLD;
            case POISON -> Color.DARKVIOLET;
        };
    }
}
