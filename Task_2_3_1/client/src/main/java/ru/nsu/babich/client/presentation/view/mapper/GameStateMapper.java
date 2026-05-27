package ru.nsu.babich.client.presentation.view.mapper;

import java.util.List;
import java.util.stream.Stream;
import ru.nsu.babich.client.presentation.api.dto.GameStateDto;
import ru.nsu.babich.client.presentation.view.model.renderable.FoodRenderable;
import ru.nsu.babich.client.presentation.view.model.renderable.Renderable;
import ru.nsu.babich.client.presentation.view.model.renderable.SnakeRenderable;

/**
 * Converts server game state DTOs into renderable view objects.
 */
public class GameStateMapper {
    private GameStateMapper() {
    }

    /**
     * Converts the given game state into a list of renderables.
     *
     * @param gameState Game state DTO.
     * @return Renderable objects for the current frame.
     */
    public static List<Renderable> toRenderables(GameStateDto gameState) {
        if (gameState == null) {
            return List.of();
        }

        return Stream.<Renderable>concat(
                gameState.players().stream()
                        .filter(player -> player.snake() != null)
                        .map(player -> new SnakeRenderable(player.snake().segments())),
                gameState.foods().stream()
                        .map(FoodRenderable::new)
        ).toList();
    }
}
