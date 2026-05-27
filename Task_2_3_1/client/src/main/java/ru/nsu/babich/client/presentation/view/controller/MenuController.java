package ru.nsu.babich.client.presentation.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ru.nsu.babich.client.dsl.ClientConfig;
import ru.nsu.babich.client.dsl.ColorPalette;
import ru.nsu.babich.client.dsl.UiConfig;
import ru.nsu.babich.client.presentation.api.WebSnakeClient;
import ru.nsu.babich.client.presentation.view.SceneManager;
import ru.nsu.babich.client.presentation.view.ViewPaths;

/**
 * Controller for the main menu.
 */
public class MenuController {
    private WebSnakeClient webSnakeClient;
    private SceneManager sceneManager;
    private ClientConfig clientConfig;

    @FXML
    private Label normalFoodLabel;
    @FXML
    private Label bonusFoodLabel;
    @FXML
    private Label poisonFoodLabel;

    public void init(WebSnakeClient webSnakeClient, SceneManager sceneManager, ClientConfig clientConfig) {
        this.webSnakeClient = webSnakeClient;
        this.sceneManager = sceneManager;
        this.clientConfig = clientConfig;
        applyConfig(clientConfig);
    }

    private void applyConfig(ClientConfig config) {
        if (config == null) {
            return;
        }
        UiConfig ui = config.ui();
        if (ui == null) {
            return;
        }
        ColorPalette palette = ui.palette();
        if (palette == null) {
            return;
        }
        applyLabelColor(normalFoodLabel, palette.foodNormal());
        applyLabelColor(bonusFoodLabel, palette.foodBonus());
        applyLabelColor(poisonFoodLabel, palette.foodPoison());
    }

    private void applyLabelColor(Label label, String color) {
        if (label == null || color == null) {
            return;
        }
        label.setTextFill(Color.web(color));
    }

    @FXML
    private void startGame() {
        SnakeGameController controller = sceneManager.setScene(ViewPaths.SNAKE_GAME);
        controller.init(webSnakeClient, clientConfig);
        webSnakeClient.join();
        webSnakeClient.addGameStateListener(controller);
    }
}