package ru.nsu.babich.client.presentation.view.mapper;

import javafx.scene.input.KeyCode;
import ru.nsu.babich.client.presentation.view.model.Direction;

/**
 * Maps keyboard keys to game directions.
 */
public class KeyboardDirectionMapper {
    private KeyboardDirectionMapper() {
    }

    /**
     * Converts the given key code into a direction.
     *
     * @param keyCode JavaFX key code.
     * @return Matching direction, or {@code null} if unsupported.
     */
    public static Direction toDirection(KeyCode keyCode) {
        if (keyCode == null) {
            return null;
        }

        return switch (keyCode) {
            case UP -> Direction.UP;
            case DOWN -> Direction.DOWN;
            case LEFT -> Direction.LEFT;
            case RIGHT -> Direction.RIGHT;
            default -> null;
        };
    }
}

