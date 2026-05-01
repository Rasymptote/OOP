package ru.nsu.babich.client.presentation.view.controller;

import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import ru.nsu.babich.client.presentation.api.WebSnakeClient;
import ru.nsu.babich.client.presentation.api.dto.FieldDto;
import ru.nsu.babich.client.presentation.api.dto.GameStateDto;
import ru.nsu.babich.client.presentation.view.FigureRenderer;
import ru.nsu.babich.client.presentation.view.GameRenderer;
import ru.nsu.babich.client.presentation.view.grid.GridRenderer;
import ru.nsu.babich.client.presentation.view.mapper.GameStateMapper;
import ru.nsu.babich.client.presentation.view.mapper.KeyboardDirectionMapper;

/**
 * Controller for the active game scene.
 */
public class SnakeGameController implements Consumer<GameStateDto> {
    @FXML
    public GridRenderer canvas;
    private WebSnakeClient webSnakeClient;
    private GameRenderer gameRenderer;
    private int appliedRowsCount = -1;
    private int appliedColumnsCount = -1;

    /**
     * Initializes the controller after FXML loading.
     */
    @FXML
    public void initialize() {
        gameRenderer = new GameRenderer(canvas, new FigureRenderer());
        canvas.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (oldScene != null) {
                oldScene.removeEventHandler(KeyEvent.KEY_PRESSED, this::onKeyPressed);
            }
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyPressed);
                newScene.getRoot().requestFocus();
            }
        });
    }

    /**
     * Supplies the WebSocket client used by the controller.
     *
     * @param webSnakeClient Connected client instance.
     */
    public void init(WebSnakeClient webSnakeClient) {
        this.webSnakeClient = webSnakeClient;
    }

    /** {@inheritDoc} */
    @Override
    public void accept(GameStateDto gameStateDto) {
        var renderables = GameStateMapper.toRenderables(gameStateDto);

        javafx.application.Platform.runLater(() -> {
            applyFieldSizeIfChanged(gameStateDto.field());
            gameRenderer.renderAll(renderables);
        });
    }

    private void applyFieldSizeIfChanged(FieldDto fieldDto) {
        if (fieldDto == null) {
            return;
        }

        int rows = fieldDto.rows();
        int columns = fieldDto.columns();
        if (rows <= 0 || columns <= 0) {
            return;
        }

        if (rows != appliedRowsCount) {
            canvas.setRowsCount(rows);
            appliedRowsCount = rows;
        }
        if (columns != appliedColumnsCount) {
            canvas.setColumnsCount(columns);
            appliedColumnsCount = columns;
        }
    }

    private void onKeyPressed(KeyEvent event) {
        var direction = KeyboardDirectionMapper.toDirection(event.getCode());
        if (direction != null) {
            webSnakeClient.changeDirection(direction.name());
        }
    }
}
