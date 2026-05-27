package ru.nsu.babich.client.presentation.view.model.renderable;

import java.util.List;
import javafx.scene.paint.Color;
import ru.nsu.babich.client.presentation.api.dto.FoodDto;
import ru.nsu.babich.client.presentation.view.model.figure.Figure;
import ru.nsu.babich.client.presentation.view.model.figure.FigureType;

/**
 * Adapts food entity for rendering.
 *
 * @param food Food entity.
 */
public record FoodRenderable(FoodDto food) implements Renderable {

    /** {@inheritDoc} */
    @Override
    public List<Figure> toFigures() {
        return List.of(new Figure(FigureType.CIRCLE, food.position(), 1,
                resolveColor(food.type())));
    }

    private Color resolveColor(String type) {
        return switch (type) {
            case "NORMAL" -> RenderablePalette.FOOD_NORMAL;
            case "BONUS" -> RenderablePalette.FOOD_BONUS;
            case "POISON" -> RenderablePalette.FOOD_POISON;
            default -> RenderablePalette.NEUTRAL;
        };
    }
}
